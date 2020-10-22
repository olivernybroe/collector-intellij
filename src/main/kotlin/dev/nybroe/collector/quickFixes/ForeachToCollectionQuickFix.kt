package dev.nybroe.collector.quickFixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.php.lang.inspections.codeStyle.PhpLoopCanBeConvertedToArrayMapInspection
import com.jetbrains.php.lang.psi.PhpPsiElementFactory
import com.jetbrains.php.lang.psi.elements.ForeachStatement
import com.jetbrains.php.lang.psi.elements.ParameterList
import com.jetbrains.php.lang.psi.elements.PhpNamedElement
import com.jetbrains.php.lang.psi.elements.Variable

class ForeachToCollectionQuickFix : LocalQuickFix {
    companion object {
        const val QUICK_FIX_NAME = "Refactor foreach to collection"
    }

    override fun getFamilyName(): String {
        return QUICK_FIX_NAME
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val foreach = descriptor.psiElement

        if (foreach !is ForeachStatement) {
            return
        }

        val statement = PhpLoopCanBeConvertedToArrayMapInspection.unwrapStatement(foreach.statement)!!

        val arguments = createArguments(project, foreach)

        val useList = createUseListStatement(
            foreach,
            arrayOf(
                foreach.key?.name,
                foreach.value?.name,
                if (foreach.array is PhpNamedElement) (foreach.array as PhpNamedElement).name else null
            ).asSequence().filterNotNull().toList()
        ) ?: ""

        val psiCollectionCall = PhpPsiElementFactory.createStatement(
            project,
            "collect(${foreach.array!!.text})->each(function(${arguments.text}) $useList {${statement.text}});"
        )

        descriptor.psiElement.replace(
            psiCollectionCall
        )
    }

    private fun createArguments(project: Project, foreach: ForeachStatement): ParameterList {
        return PhpPsiElementFactory.createArgumentList(
            project,
            foreach.value!!.text +
                if (foreach.key != null) ", ${foreach.key!!.text}" else ""
        )
    }

    private fun createUseListStatement(foreach: ForeachStatement, ignoredNames: List<String>): String? {
        return PsiTreeUtil.collectElementsOfType(foreach, Variable::class.java)
            .map { it.name }
            .filter { !ignoredNames.contains(it) }
            .ifEmpty { return null }
            .joinToString(separator = ", ") { "$$it" }
            .let { "use ($it)" }
    }
}
