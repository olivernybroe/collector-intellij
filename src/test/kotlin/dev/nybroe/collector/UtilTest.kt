package dev.nybroe.collector

import com.jetbrains.php.lang.psi.resolve.types.PhpType

internal class UtilTest : BaseCollectTestCase() {
    fun testIsCollection() {
        assertTrue(
            PhpType().add(
                "\\Illuminate\\Support\\Collection"
            ).isCollection(project)
        )
    }

    fun testMixedIsNotCollection() {
        assertFalse(PhpType.MIXED.isCollection(project))
    }

    fun testIterableIsNotCollection() {
        assertFalse(PhpType.ITERABLE.isCollection(project))
    }

    fun testArrayIsNotCollection() {
        assertFalse(PhpType.ARRAY.isCollection(project))
    }

    fun testEmptyIsNotCollection() {
        assertFalse(PhpType.EMPTY.isCollection(project))
    }
}
