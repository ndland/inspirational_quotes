package me.nickland.inspirationalquote

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.array
import com.beust.klaxon.obj
import com.beust.klaxon.string
import me.nickland.inspirationalquote.activity.InspirationalQuoteActivity
import me.nickland.inspirationalquote.constants.Constants
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class InspirationalQuoteInstrumentedTest {

    private lateinit var server: MockWebServer
    private var expectedQuote: String? = null
    private var expectedError: String? = null
    private val parser = Parser()
    private val successResponse = "successfulResponse.json"
    private val errorResponse = "errorResponse.json"
    private val tooManyRequestsResponse = "tooManyRequestsResponse.json"

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<InspirationalQuoteActivity> = ActivityTestRule(InspirationalQuoteActivity::class.java, false, false)

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        Constants.BASE_URL = server.url("/").toString()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    /**
     * Make sure that on a successful response, the app will display
     * the Quote of the Day
     */
    @Test
    fun ensureTheQuoteOfTheDayIsDisplayed() {
        buildJsonObject(readFile(successResponse)).let {
            expectedQuote = buildJsonObject(readFile(successResponse))
                    .obj("contents")
                    ?.array<JsonObject>("quotes")
                    ?.get(0)
                    ?.string("quote")
        }

        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(readFile(successResponse)))

        launchActivity()

        onView(withId(R.id.inspirationalQuote))
                .check(matches(withText(expectedQuote)))
    }

    /**
     * Make sure that if the response comes back with an HTTP status
     * of 404, we display the error message that comes back in the
     * response
     */
    @Test
    fun ensureTheApplicationHandlesErrors() {
        buildJsonObject(readFile(errorResponse)).let {
            expectedError = buildJsonObject(readFile(errorResponse))
                    .obj("error")
                    ?.string("message")
        }

        server.enqueue(MockResponse()
                .setResponseCode(404)
                .setBody(readFile(errorResponse)))

        launchActivity()

        onView(withId(R.id.inspirationalQuote))
                .check(matches(withText(expectedError)))
    }

    /**
     * Make sure that the app displays the message about too many
     * requests when the request limit has been exceeded.
     */
    @Test
    fun ensureTheApplicationHandlesTooManyRequestErrors() {
        buildJsonObject(readFile(tooManyRequestsResponse)).let {
            expectedError = buildJsonObject(readFile(tooManyRequestsResponse))
                    .obj("error")
                    ?.string("message")
        }

        server.enqueue(MockResponse()
                .setResponseCode(429)
                .setBody(readFile(tooManyRequestsResponse)))

        launchActivity()

        onView(withId(R.id.inspirationalQuote))
                .check(matches(withText(expectedError)))
    }

    private fun launchActivity() {
        val intent = Intent()
        mActivityRule.launchActivity(intent)
    }

    private fun buildJsonObject(response: String?): JsonObject {
        return parser.parse(StringBuilder(response)) as JsonObject
    }

    private fun readFile(fileName: String) =
            this::class.java.classLoader.getResource(fileName)?.readText()

    companion object {
        val TAG = InspirationalQuoteInstrumentedTest::class.java.simpleName
    }
}

