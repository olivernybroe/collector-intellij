package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.quickFixes.ForeachToCollectionQuickFix

internal class ForeachToCollectionInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = ForeachToCollectionInspection()
    override fun defaultAction(): String = ForeachToCollectionQuickFix.QUICK_FIX_NAME
    override fun getTestDataPath(): String = "src/test/resources/inspections/ForeachToCollectionInspection"

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
