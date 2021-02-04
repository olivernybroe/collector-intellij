package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor
import dev.nybroe.collector.MyBundle
import dev.nybroe.collector.isCollectionMethod
import dev.nybroe.collector.quickFixes.WhereIsNotEmptyToContainsQuickFix

class WhereIsNotEmptyInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpMethodReference(reference: MethodReference) {
                // Check that method reference is collection method.
                if (!reference.isCollectionMethod) {
                    return
                }

                // Check that method reference is a where method.
                if (reference.name != "where") {
                    return
                }

                // Check that the next method reference is first with no arguments.
                val firstReference = reference.parent as? MethodReference ?: return
                if (firstReference.name != "isNotEmpty") {
                    return
                }

                holder.registerProblem(
                    firstReference,
                    MyBundle.message("whereIsNotEmptyDescription"),
                    ProblemHighlightType.WEAK_WARNING,
                    WhereIsNotEmptyToContainsQuickFix()
                )
            }
        }
    }
}
