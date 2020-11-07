package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.quickFixes.ForeachToCollectionQuickFix

internal class ForeachToCollectionInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = ForeachToCollectionInspection()
    override fun defaultAction(): String = ForeachToCollectionQuickFix.QUICK_FIX_NAME

    fun testForeachValueOnly() {
        doTest("foreach-value-only")
    }

    fun testForeachKeyValue() {
        doTest("foreach-key-value")
    }

    fun testForeachOuterScopeVariable() {
        doTest("foreach-outer-scope-variable")
    }

    fun testForeachKeyValueMultiStatement() {
        doTest("foreach-key-value-multi-statement")
    }

    fun testForeachOuterScopeThisVariable() {
        doTest("foreach-outer-scope-this-variable")
    }

    fun testForeachUsingForeachArrayVariable() {
        doTest("foreach-using-foreach-array-variable")
    }

    fun testForeachOuterScopeVariableUsedTwice() {
        doTest("foreach-outer-scope-variable-used-twice")
    }

    fun testForeachArrayVariableUsingPropertyAccessor() {
        doTest("foreach-array-variable-using-property-accessor")
    }

    fun testForeachStringInterpolation() {
        doTest("foreach-string-interpolation")
    }

    fun testForeachStringInterpolationWithoutBraces() {
        doTest("foreach-string-interpolation-without-braces")
    }

    fun testForeachStringInterpolationWithPropertyAccess() {
        doTest("foreach-string-interpolation-with-property-access")
    }
}
