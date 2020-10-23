package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase

internal abstract class InspectionTest : BasePlatformTestCase() {
    protected abstract fun defaultInspection(): InspectionProfileEntry
    protected abstract fun defaultAction(): String

    protected fun doTest(testName: String) = doTest(testName, defaultInspection(), defaultAction())

    /**
     * Given the name of a test file, runs comparing references inspection quick fix and tests
     * the results against a reference outcome file. File name pattern 'foo.java' and 'foo.after.java'
     * are matching before and after files in the testData directory.
     *
     * @param testName The name of the test file before comparing references inspection.
     */
    protected fun doTest(testName: String, inspection: InspectionProfileEntry, action: String) {
        // Initialize the test based on the testData file
        myFixture.configureByFile("$testName.php")

        // Initialize the inspection and get a list of highlighted
        myFixture.enableInspections(inspection)
        val highlightInfos = myFixture.doHighlighting()
        TestCase.assertFalse(highlightInfos.isEmpty())

        // Get the quick fix action for comparing references inspection and apply it to the file
        myFixture.findSingleIntention(action).also {
            assertNotNull(it)
            myFixture.launchAction(it)
        }

        // Verify the results
        myFixture.checkResultByFile("$testName.after.php")
    }
}
