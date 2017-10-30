package me.nickland.inspirationalquote

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        val quote = response200.getJSONObject("contents").getJSONArray("quotes").getJSONObject(0).getString("quote")
//        val activity = mActivityRule.launchActivity(Intent(InstrumentationRegistry.getContext(), InspirationalQuoteActivity::class.java))
//        Log.d("TAG", jsonBody.toString())
//        stubFor(get(urlMatching("/qod"))
//                .willReturn(aResponse() //                        .withStatus(200)
//                        .withBody("stuff")))
//        val intent = Intent()
//        intent.putExtra("quote", "A new quote of the day")
//        mActivityRule.launchActivity(intent)
        onView(withId(R.id.inspirationalQuote))
                .check(matches(withText("A brand new inspirational quote")))
    }
}

