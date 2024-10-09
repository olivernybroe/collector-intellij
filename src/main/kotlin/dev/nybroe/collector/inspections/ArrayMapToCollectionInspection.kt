package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor
import dev.nybroe.collector.MyBundle
import dev.nybroe.collector.isGlobalFunctionCallWithName
import dev.nybroe.collector.quickFixes.ArrayMapToCollectionQuickFix

class ArrayMapToCollectionInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpFunctionCall(reference: FunctionReference) {
                if (!isOnTheFly) return

                if (!reference.isGlobalFunctionCallWithName("array_map")) {
                    return
                }

                holder.registerProblem(
                    reference,
                    MyBundle.message("arrayMapToCollectionDescription"),
                    ProblemHighlightType.INFORMATION,
                    ArrayMapToCollectionQuickFix()
                )
            }
        }
    }
}
