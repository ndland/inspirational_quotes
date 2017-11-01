package me.nickland.inspirationalquote.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.TextView
import me.nickland.inspirationalquote.R
import me.nickland.inspirationalquote.api.QuoteOfTheDayApi
import me.nickland.inspirationalquote.service.QuoteOfTheDayService
import org.json.JSONObject
import java.net.URL

class InspirationalQuoteActivity : AppCompatActivity() {

    private val quoteService = QuoteOfTheDayService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspirational_quote)
        val textView = findViewById<TextView>(R.id.inspirationalQuote) as TextView

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        textView.text = getQuoteOfTheDay()
    }

    private fun getQuoteOfTheDay(): String {
        val qod = quoteService.getQuoteOfTheDay()
        val response = qod.execute()
        Log.e(TAG, "Response body: ${response.body()?.contents?.quotes?.get(0)?.quote}")
        return ""

//        return JSONObject(URL("https://quotes.rest/qod").readText())
//                .getJSONObject("contents")
//                .getJSONArray("quotes")
//                .getJSONObject(0)
//                .getString("quote")
    }

    companion object {
        private val TAG = InspirationalQuoteActivity::class.java.simpleName
    }
}
