package dev.nybroe.collector

import com.intellij.testFramework.fixtures.BasePlatformTestCase

@Suppress("UnnecessaryAbstractClass")
internal abstract class BaseCollectTestCase : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/resources"

    override fun setUp() {
        super.setUp()
        myFixture.copyFileToProject("stubs.php")
    }
}
