package dev.nybroe.collector.quickFixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.jetbrains.php.lang.psi.PhpPsiElementFactory
import com.jetbrains.php.lang.psi.elements.FunctionReference

class ArrayMapToCollectionQuickFix : LocalQuickFix {
    companion object {
        const val QUICK_FIX_NAME = "Refactor 'array_map' to collection"
    }

    override fun getFamilyName(): String {
        return QUICK_FIX_NAME
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val arrayMap = descriptor.psiElement

        if (arrayMap !is FunctionReference) {
            return
        }

        val parameters = arrayMap.parameters

        // Expects two parameters, a function and the data.
        if (parameters.count() != 2) {
            return
        }

        descriptor.psiElement.replace(
            PhpPsiElementFactory.createMethodReference(
                project,
                "collect(${parameters[1].text})->map(${parameters[0].text})->all()"
            )
        )
    }
}
