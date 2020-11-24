package dev.nybroe.collector.inspections

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.elementType
import com.jetbrains.php.config.PhpLanguageFeature
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.lexer.PhpTokenTypes
import com.jetbrains.php.lang.psi.PhpPsiUtil
import com.jetbrains.php.lang.psi.elements.Function
import com.jetbrains.php.lang.psi.elements.GroupStatement
import com.jetbrains.php.lang.psi.elements.MethodReference
import com.jetbrains.php.lang.psi.elements.ParameterList
import com.jetbrains.php.lang.psi.elements.PhpUseList
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor
import dev.nybroe.collector.MyBundle
import dev.nybroe.collector.isCollectionMethod
import dev.nybroe.collector.isShortArrowFunction
import dev.nybroe.collector.quickFixes.ClosureToArrowFunctionQuickFix

class ClosureToArrowFunctionInspection : PhpInspection() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpFunction(closure: Function) {
                // Check that the PHP version supports arrow functions
                if (!PhpLanguageFeature.ARROW_FUNCTION_SYNTAX.isSupported(closure.project)) {
                    return
                }

                // Make sure we are in a closure
                if (!closure.isClosure) {
                    return
                }

                // And not already using arrow functions
                if (closure.isShortArrowFunction) {
                    return
                }

                // And no use arguments are by reference
                if (useByReferenceExists(closure)) {
                    return
                }

                // And closure is inside a collection.
                val methodReference = PhpPsiUtil
                    .getParentByCondition<ParameterList>(closure, ParameterList.INSTANCEOF)
                    ?.parent as? MethodReference ?: return

                if (!methodReference.isCollectionMethod) {
                    return
                }

                // Get the closure body and make sure the size is 1
                val body = PhpPsiUtil.getChildByCondition<GroupStatement>(closure, GroupStatement.INSTANCEOF) ?: return

                if (body.statements.size != 1) {
                    return
                }

                // And cannot be done on echo call
                if (body.statements[0].firstChild.elementType === PhpTokenTypes.kwECHO) {
                    return
                }

                holder.registerProblem(
                    closure,
                    MyBundle.message("closureToArrowFunctionDescription"),
                    ProblemHighlightType.WEAK_WARNING,
                    ClosureToArrowFunctionQuickFix()
                )
            }
        }
    }

    private fun useByReferenceExists(closure: Function): Boolean {
        val useList = PhpPsiUtil
            .getChildByCondition<PsiElement>(closure, PhpUseList.INSTANCEOF) as PhpUseList? ?: return false

        return useList.children
            .any { it.prevSibling.elementType === PhpTokenTypes.opBIT_AND }
    }
}
