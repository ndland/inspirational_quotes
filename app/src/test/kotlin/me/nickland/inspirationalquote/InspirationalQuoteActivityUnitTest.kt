package me.nickland.inspirationalquote

import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class InspirationalQuoteActivityUnitTest {

    @Test
    @Throws(Exception::class)
    fun assertInspirationalQuoteActivityNotNull() {
        val inspirationalQuote = InspirationalQuoteActivity()
        assertNotNull(inspirationalQuote)
    }
}