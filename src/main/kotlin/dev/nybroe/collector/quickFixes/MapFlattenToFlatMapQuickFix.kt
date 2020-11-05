package dev.nybroe.collector.quickFixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.jetbrains.php.lang.psi.elements.MethodReference
import dev.nybroe.collector.CollectionPsiFactory

class MapFlattenToFlatMapQuickFix : LocalQuickFix {
    companion object {
        const val QUICK_FIX_NAME = "Refactor 'map()->flatten()' to collection"
    }

    override fun getFamilyName(): String {
        return QUICK_FIX_NAME
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val flattenMethodReference = descriptor.psiElement as? MethodReference ?: return
        val mapMethodReference = flattenMethodReference.firstChild as? MethodReference ?: return

        val methodNameNode = mapMethodReference.nameNode ?: return

        // Replace the method name from map -> flatMap.
        mapMethodReference.node.replaceChild(
            methodNameNode,
            CollectionPsiFactory.createIdentifier(
                project,
                "flatMap"
            ).node
        )

        // Remove flatten method.
        descriptor.psiElement.replace(flattenMethodReference.firstChild)
    }
}
