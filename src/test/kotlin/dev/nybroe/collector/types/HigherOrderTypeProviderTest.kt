package dev.nybroe.collector.types

import dev.nybroe.collector.BaseCollectTestCase

internal class HigherOrderTypeProviderTest : BaseCollectTestCase() {
    fun testHigherOrderPropertyReturnsCollection() {
        myFixture.configureByFile(
            "types/HigherOrderMethodsTypeProvider/higherOrderProperty.php"
        )

        assertCompletion("map", "each")
    }

    fun testHigherOrderMethodReturnsCollection() {
        myFixture.configureByFile(
            "types/HigherOrderMethodsTypeProvider/higherOrderMethod.php"
        )

        assertCompletion("map", "each")
    }

    private fun assertCompletion(vararg shouldContain: String) {
        myFixture.completeBasic()

        val strings = myFixture.lookupElementStrings ?: return fail("empty completion result")

        assertContainsElements(strings, shouldContain.asList())
    }
}
