package dev.nybroe.collector.quickFixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.jetbrains.php.lang.psi.elements.FunctionReference

class NestedCollectionQuickFix : LocalQuickFix {
    companion object {
        const val QUICK_FIX_NAME = "Refactor nested collection."
    }

    override fun getFamilyName(): String {
        return QUICK_FIX_NAME
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val collectReference = descriptor.psiElement as? FunctionReference ?: return

        descriptor.psiElement.replace(
            collectReference.parameters[0]
        )
    }
}
