package dev.nybroe.collector

import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiElement
import com.jetbrains.php.lang.PhpLangUtil
import com.jetbrains.php.lang.psi.PhpPsiUtil
import com.jetbrains.php.lang.psi.elements.Function
import com.jetbrains.php.lang.psi.elements.FunctionReference
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression
import com.jetbrains.php.lang.psi.elements.Variable

fun FunctionReference.isGlobalFunctionCallWithName(name: String): Boolean {
    return PhpLangUtil.isGlobalNamespaceFQN(this.namespaceName) && StringUtil.equals(this.name, name)
}


fun PsiElement.canBeCalledAsLambda(): Boolean {
    return this is Variable || this is StringLiteralExpression
}


fun PsiElement.resolveFunction(): Function? {
    if (this.canBeCalledAsLambda()) {
        val references = this.references
        if (references.size == 1) {
            return references[0].resolve() as? Function
        }
    }
    return PhpPsiUtil.getChildByCondition<PsiElement>(this, Function.INSTANCEOF) as Function?
}