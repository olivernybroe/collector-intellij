package dev.nybroe.collector

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.jetbrains.php.lang.lexer.PhpTokenTypes
import com.jetbrains.php.lang.psi.PhpPsiElementFactory

object CollectionPsiFactory {
    fun createIdentifier(project: Project, text: String): PsiElement {
        return PhpPsiElementFactory.createFromText(
            project,
            PhpTokenTypes.IDENTIFIER,
            text
        )
    }
}
