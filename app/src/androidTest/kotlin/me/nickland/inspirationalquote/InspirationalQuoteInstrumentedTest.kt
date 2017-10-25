package me.nickland.inspirationalquote

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class InspirationalQuoteInstrumentedTest {

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<InspirationalQuoteActivity> = ActivityTestRule(InspirationalQuoteActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("me.nickland.inspirationalquote", appContext.packageName)
    }

    @Test
    fun ensureTheQuoteOfTheDayIsDisplayed() {
        onView(withId(R.id.inspirationalQuote))
                .check(matches(withText("A brand new inspirational quote")))
    }
}
