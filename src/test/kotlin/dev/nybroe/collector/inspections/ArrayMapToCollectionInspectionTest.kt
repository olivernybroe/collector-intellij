package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.quickFixes.ArrayMapToCollectionQuickFix

internal class ArrayMapToCollectionInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = ArrayMapToCollectionInspection()
    override fun defaultAction(): String = ArrayMapToCollectionQuickFix.QUICK_FIX_NAME

    fun testCallbackAndVariable() {
        doTest("array_map-callback-and-variable")
    }

    fun testCallbackAndArray() {
        doTest("array_map-callback-and-array")
    }

    fun testInNamespace() {
        doTest("array_map-in-namespace")
    }

    fun testNamespacedArrayMap() {
        doTest("namespaced_array_map")
    }
}
