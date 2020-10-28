package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry
import dev.nybroe.collector.quickFixes.MapFlattenToFlatMapQuickFix

internal class MapFlattenToFlatMapInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = MapFlattenToFlatMapInspection()
    override fun defaultAction(): String = MapFlattenToFlatMapQuickFix.QUICK_FIX_NAME

    fun testMapFlattenToFlatMap() {
        doTest("map_flatten_to_flatmap")
    }

    fun testWithOtherMethodCalls() {
        doTest("map_flatten_to_flatmap_with_other_method_calls")
    }

    fun testMapFlattenDepth2() {
        doNotMatchTest("map_flatten_depth_2")
    }

    fun testMapFlattenDepthInfinite() {
        doNotMatchTest("map_flatten_depth_infinite")
    }
}
