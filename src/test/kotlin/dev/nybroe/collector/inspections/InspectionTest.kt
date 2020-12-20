package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.BaseCollectTestCase

internal abstract class InspectionTest : BaseCollectTestCase() {
    protected abstract fun defaultInspection(): InspectionProfileEntry
    protected abstract fun defaultAction(): String

    private fun defaultInspectionPath(): String {
        return "inspections/${defaultInspection()::class.simpleName}"
    }

    protected fun doTest(testName: String) = doTest(
        "${defaultInspectionPath()}/$testName",
        defaultInspection()
    )

    protected fun doTest(testName: String, inspection: InspectionProfileEntry) {
        // Initialize the test based on the testData file
        val file = myFixture.configureByFile("$testName.php")

        // Initialize the inspection and get a list of highlighted
        myFixture.enableInspections(inspection)
        myFixture.testHighlighting(true, false, true, file.virtualFile)

        myFixture.getAllQuickFixes().forEach { myFixture.launchAction(it) }

        // Verify the results
        myFixture.checkResultByFile("$testName.after.php")
    }

    protected fun doNotMatchTest(testName: String) = doNotMatchTest(
        "${defaultInspectionPath()}/nonMatches/$testName",
        defaultInspection()
    )

    protected fun doNotMatchTest(testName: String, inspection: InspectionProfileEntry) {
        // Initialize the test based on the testData file
        val file = myFixture.configureByFile("$testName.php")

        // Initialize the inspection and get a list of highlighted
        myFixture.enableInspections(inspection)
        myFixture.testHighlighting(true, false, true, file.virtualFile)

        assertEmpty(myFixture.getAllQuickFixes())
    }
}
