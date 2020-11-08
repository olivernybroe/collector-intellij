package dev.nybroe.collector.quickFixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.jetbrains.php.lang.lexer.PhpTokenTypes
import com.jetbrains.php.lang.psi.PhpPsiElementFactory
import com.jetbrains.php.lang.psi.PhpPsiUtil
import com.jetbrains.php.lang.psi.elements.Function
import com.jetbrains.php.lang.psi.elements.GroupStatement
import com.jetbrains.php.lang.psi.elements.PhpUseList

class ClosureToArrowFunctionQuickFix : LocalQuickFix {
    companion object {
        const val QUICK_FIX_NAME = "Closure can be converted to arrow function"
    }

    override fun getFamilyName(): String {
        return QUICK_FIX_NAME
    }

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val closure = descriptor.psiElement as? Function ?: return

        val body = PhpPsiUtil.getChildByCondition<GroupStatement>(closure, GroupStatement.INSTANCEOF) ?: return

        // Replace `function` with `fn`
        val functionKeyword = closure.node.findChildByType(PhpTokenTypes.kwFUNCTION)?.psi ?: return
        functionKeyword.replace(PhpPsiElementFactory.createFromText(project, PhpTokenTypes.kwFN, "fn()"))

        // Replace body with dummy arrow function
        val arrow = body.replace(PhpPsiElementFactory.createFromText(project, PhpTokenTypes.opHASH_ARRAY, "fn() => 1"))

        // Add body
        arrow.parent.addAfter(copyBody(body), arrow)

        // Delete use list
        PhpPsiUtil.getChildByCondition<PhpUseList>(closure, PhpUseList.INSTANCEOF)?.delete()
    }

    private fun copyBody(body: GroupStatement): PsiElement {
        val statement = body.statements[0].copy()

        // Delete semi colon
        if (statement.lastChild.elementType === PhpTokenTypes.opSEMICOLON) {
            statement.lastChild.delete()
        }

        // Delete return keyword if exist
        if (statement.firstChild.elementType === PhpTokenTypes.kwRETURN) {
            statement.firstChild.delete()
        }

        return statement
    }
}
