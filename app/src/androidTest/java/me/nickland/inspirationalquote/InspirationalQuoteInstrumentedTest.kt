package me.nickland.inspirationalquote

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import me.nickland.inspirationalquote.activity.InspirationalQuoteActivity
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URL

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class InspirationalQuoteInstrumentedTest {

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<InspirationalQuoteActivity> = ActivityTestRule(InspirationalQuoteActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("me.nickland.inspirationalquote", appContext.packageName)
    }

    @Test
    fun ensureTheQuoteOfTheDayIsDisplayed() {
        val response200 = JSONObject(this::class.java.classLoader.getResource("200.json").readText())
        val expectedQuote = response200
                .getJSONObject("contents")
                .getJSONArray("quotes")
                .getJSONObject(0)
                .getString("quote")
        val actualQuote = JSONObject(URL("https://quotes.rest/qod").readText())
                .getJSONObject("contents")
                .getJSONArray("quotes")
                .getJSONObject(0)
                .getString("quote")
        onView(withId(R.id.inspirationalQuote))
                .check(matches(withText(actualQuote)))
    }
}

