package dev.nybroe.collector.blade

import com.intellij.codeInspection.InspectionSuppressor
import com.intellij.codeInspection.SuppressQuickFix
import com.intellij.psi.PsiElement
import com.jetbrains.php.blade.parser.BladeCompositeElement
import com.jetbrains.php.blade.psi.BladePsiDirective
import com.jetbrains.php.blade.psi.BladePsiDirectiveParameter
import com.jetbrains.php.blade.psi.BladePsiLanguageInjectionHost
import com.jetbrains.php.blade.psi.BladePsiPhpBlock
import com.jetbrains.php.blade.psi.BladeTokenTypes
import dev.nybroe.collector.inspections.ForeachToCollectionInspection

class BladePhpInspectionSuppressor : InspectionSuppressor {
    companion object {
        private val SUPPRESSED_PHP_INSPECTIONS = listOf(ForeachToCollectionInspection().id)
    }

    override fun isSuppressedFor(element: PsiElement, toolId: String): Boolean {
        if (element !is BladeCompositeElement) {
            return false
        }

        if (element is BladePsiPhpBlock) {
            return false
        }

        if (element is BladePsiDirectiveParameter && element.directiveElementType === BladeTokenTypes.PHP_DIRECTIVE) {
            return false
        }

        return SUPPRESSED_PHP_INSPECTIONS.contains(toolId)
    }

    override fun getSuppressActions(element: PsiElement?, toolId: String): Array<SuppressQuickFix> {
        return SuppressQuickFix.EMPTY_ARRAY
    }
}
