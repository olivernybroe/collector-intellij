package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.quickFixes.WhereFirstToFirstWhereQuickFix

internal class WhereFirstInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = WhereFirstInspection()
    override fun defaultAction(): String = WhereFirstToFirstWhereQuickFix.QUICK_FIX_NAME

    fun testWhereFirstToFirstWhere() {
        doTest("where-first-to-firstWhere")
    }

    fun testWhereFirstWithArgumentsInFirst() {
        doNotMatchTest("where-first-with-arguments-in-first")
    }
}
