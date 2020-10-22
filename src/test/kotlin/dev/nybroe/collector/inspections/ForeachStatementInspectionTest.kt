package dev.nybroe.collector.inspections

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import dev.nybroe.collector.quickFixes.ForeachToCollectionQuickFix
import junit.framework.TestCase


internal class ForeachStatementInspectionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/resources/inspections/ForeachStatementInspection"
    }

    /**
     * Given the name of a test file, runs comparing references inspection quick fix and tests
     * the results against a reference outcome file. File name pattern 'foo.java' and 'foo.after.java'
     * are matching before and after files in the testData directory.
     *
     * @param testName The name of the test file before comparing references inspection.
     */
    private fun doTest(testName: String) {
        // Initialize the test based on the testData file
        myFixture.configureByFile("$testName.php")
        // Initialize the inspection and get a list of highlighted
        myFixture.enableInspections(ForeachToCollectionInspection())
        val highlightInfos = myFixture.doHighlighting()
        TestCase.assertFalse(highlightInfos.isEmpty())
        // Get the quick fix action for comparing references inspection and apply it to the file
        val action = myFixture.findSingleIntention(ForeachToCollectionQuickFix.QUICK_FIX_NAME)
        TestCase.assertNotNull(action)
        myFixture.launchAction(action)
        // Verify the results
        myFixture.checkResultByFile("$testName.after.php")
    }

    fun testForeachValueOnly() {
        doTest("foreach-value-only")
    }

    fun testForeachKeyValue() {
        doTest("foreach-key-value")
    }

    fun testForeachOuterScopeVariable() {
        doTest("foreach-outer-scope-variable")
    }
}
