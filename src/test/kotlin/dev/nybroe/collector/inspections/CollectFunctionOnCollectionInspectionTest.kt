package dev.nybroe.collector.inspections

import com.intellij.codeInspection.InspectionProfileEntry

internal class CollectFunctionOnCollectionInspectionTest : InspectionTest() {
    override fun defaultInspection(): InspectionProfileEntry = CollectFunctionInCollectionInspection()
    override fun defaultAction(): String = ""

    fun testCollectOnCollectVariable() {
        doTest("collect_on_collect_variable")
    }

    fun testCollectInCollect() {
        doTest("collect_in_collect")
    }

    fun testCollectOnEloquentCollectVariable() {
        doTest("collect_on_eloquent_collect_variable")
    }

    fun testCollectOnMixedVariable() {
        doNotMatchTest("collect_on_mixed_variable")
    }
}
