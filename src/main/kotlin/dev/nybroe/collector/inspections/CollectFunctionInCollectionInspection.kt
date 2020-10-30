package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.elements.PhpReference
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor
import dev.nybroe.collector.MyBundle
import dev.nybroe.collector.isCollectionType
import dev.nybroe.collector.isGlobalFunctionCallWithName
import dev.nybroe.collector.quickFixes.NestedCollectionQuickFix

class CollectFunctionInCollectionInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpFunctionCall(reference: FunctionReference) {
                // Make sure we are calling the collect function
                if (!reference.isGlobalFunctionCallWithName("collect")) {
                    return
                }

                // collect call should only have one argument.
                if (reference.parameters.size != 1) {
                    return
                }

                if ((reference.parameters[0].reference as? PhpReference)?.isCollectionType != true) {
                    return
                }

                holder.registerProblem(
                    reference,
                    MyBundle.message("collectFunctionInCollectionInspectionDescription"),
                    ProblemHighlightType.WARNING,
                    NestedCollectionQuickFix()
                )
            }
        }
    }
}
