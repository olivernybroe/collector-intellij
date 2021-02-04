package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.quickFixes.WhereIsNotEmptyToContainsQuickFix

internal class WhereIsNotEmptyInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = WhereIsNotEmptyInspection()
    override fun defaultAction(): String = WhereIsNotEmptyToContainsQuickFix.QUICK_FIX_NAME

    fun testWhereIsNotEmptyToContains() {
        doTest("where-isNotEmpty-to-contains")
    }
}
