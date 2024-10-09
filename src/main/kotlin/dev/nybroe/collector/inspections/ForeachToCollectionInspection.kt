package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.ForeachStatement
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor
import dev.nybroe.collector.MyBundle
import dev.nybroe.collector.quickFixes.ForeachToCollectionQuickFix

class ForeachToCollectionInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpForeach(foreach: ForeachStatement) {
                if (!isOnTheFly) return

                holder.registerProblem(
                    foreach,
                    MyBundle.message("foreachToCollectionDescription"),
                    ProblemHighlightType.INFORMATION,
                    ForeachToCollectionQuickFix()
                )
            }
        }
    }
}
