package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.elements.PhpExpression
import com.jetbrains.php.lang.psi.resolve.types.PhpType
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor
import dev.nybroe.collector.MyBundle
import dev.nybroe.collector.isCollectionMethod
import dev.nybroe.collector.quickFixes.MapFlattenToFlatMapQuickFix

class MapFlattenToFlatMapInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpMethodReference(reference: MethodReference) {
                // Check that method reference is collection method.
                if (!reference.isCollectionMethod()) {
                    return
                }

                // Check that method reference is a map method.
                if (reference.name != "map") {
                    return
                }

                // Check that the next method reference is flatten with "1" as first and only parameter.
                val flattenReference = reference.parent as? MethodReference ?: return
                if (flattenReference.name != "flatten") {
                    return
                }
                if (flattenReference.parameters.size != 1) {
                    return
                }
                if ((flattenReference.parameters[0] as? PhpExpression)?.declaredType != PhpType.INT) {
                    return
                }
                if (flattenReference.parameters[0].text != "1") {
                    return
                }

                holder.registerProblem(
                    flattenReference,
                    MyBundle.message("mapFlattenToFlatMapDescription"),
                    ProblemHighlightType.WEAK_WARNING,
                    MapFlattenToFlatMapQuickFix()
                )
            }
        }
    }
}
