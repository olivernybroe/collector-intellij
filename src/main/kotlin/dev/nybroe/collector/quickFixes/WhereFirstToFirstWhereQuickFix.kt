package dev.nybroe.collector.quickFixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.jetbrains.php.lang.psi.elements.MethodReference
import dev.nybroe.collector.CollectionPsiFactory

class WhereFirstToFirstWhereQuickFix : LocalQuickFix {
    companion object {
        const val QUICK_FIX_NAME = "Refactor 'where()->first()' to 'firstWhere()'"
    }

    override fun getFamilyName(): String {
        return QUICK_FIX_NAME
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val firstReference = descriptor.psiElement as? MethodReference ?: return
        val whereMethodReference = firstReference.firstChild as? MethodReference ?: return

        val methodNameNode = whereMethodReference.nameNode ?: return

        // Replace the method name from where -> firstWhere.
        whereMethodReference.node.replaceChild(
            methodNameNode,
            CollectionPsiFactory.createIdentifier(
                project,
                "firstWhere"
            ).node
        )

        // Remove first method.
        descriptor.psiElement.replace(firstReference.firstChild)
    }
}
