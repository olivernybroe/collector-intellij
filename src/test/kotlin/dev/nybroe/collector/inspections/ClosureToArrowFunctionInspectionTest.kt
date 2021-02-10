package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import com.jetbrains.php.config.PhpLanguageLevel
import com.jetbrains.php.config.PhpProjectConfigurationFacade
import dev.nybroe.collector.quickFixes.ClosureToArrowFunctionQuickFix

internal class ClosureToArrowFunctionInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = ClosureToArrowFunctionInspection()
    override fun defaultAction(): String = ClosureToArrowFunctionQuickFix.QUICK_FIX_NAME

    fun testEachFunctionCallToArrow() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doTest("each-function-call-to-arrow-function")
    }

    fun testEachFunctionCallToArrowWithUses() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doTest("each-function-call-to-arrow-function-with-uses")
    }

    fun testEachFunctionCallToArrowNotOnPHP730() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP730
        doNotMatchTest("each-function-call-to-arrow-function-php730")
    }

    fun testEachFunctionCallToArrowWithReturn() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doTest("each-function-call-to-arrow-function-with-return")
    }

    fun testEachFunctionCallToArrowNotOnUsesByReference() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doNotMatchTest("each-function-call-to-arrow-function-with-uses-by-reference")
    }

    fun testEachFunctionCallToArrowNotOnEchoCall() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doNotMatchTest("each-function-call-to-arrow-function-echo")
    }

    fun testFunctionCallToArrowFunctionNotOnNonCollectionFunctions() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doNotMatchTest("function-call-to-arrow-function-not-in-collection")
    }

    fun testEachFunctionCallToArrowFromVariable() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doTest("each-function-call-to-arrow-function-from-variable")
    }

    fun testClosureWithIfStatementCannotBeChanged() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doNotMatchTest("if-closure-to-arrow-function")
    }

    fun testClosureWithForeachStatementCannotBeChanged() {
        PhpProjectConfigurationFacade.getInstance(project).languageLevel = PhpLanguageLevel.PHP740
        doNotMatchTest("foreach-closure-to-arrow-function")
    }
}
