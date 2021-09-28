package dev.nybroe.collector.types

import dev.nybroe.collector.BaseCollectTestCase
import org.junit.Ignore

@Ignore
internal class CollectionTypeProviderTest : BaseCollectTestCase() {
    fun testCollectionTypeArraySyntax() {
        myFixture.configureByFile(
            "types/CollectionTypeProvider/CollectionTypeArraySyntax.php"
        )

        assertCompletion("myMethod", "mySecondMethod")
    }

    fun testCollectionTypeGenericSyntax() {
        myFixture.configureByFile(
            "types/CollectionTypeProvider/CollectionTypeGenericSyntax.php"
        )

        assertCompletion("myMethod", "mySecondMethod")
    }

    private fun assertCompletion(vararg shouldContain: String) {
        myFixture.completeBasic()

        val strings = myFixture.lookupElementStrings ?: return fail("empty completion result")

        assertContainsElements(strings, shouldContain.asList())
    }
}
