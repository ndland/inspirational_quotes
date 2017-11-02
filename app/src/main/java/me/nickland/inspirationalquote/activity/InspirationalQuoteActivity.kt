package me.nickland.inspirationalquote.activity

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import me.nickland.inspirationalquote.R
import me.nickland.inspirationalquote.service.QuoteOfTheDayService

class InspirationalQuoteActivity : AppCompatActivity() {

    private lateinit var quoteService: QuoteOfTheDayService
    private var quote: String = ""
    private var author: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspirational_quote)
        val textView = findViewById<TextView>(R.id.inspirationalQuote) as TextView

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        textView.text = getQuoteOfTheDay()
    }

    private fun getQuoteOfTheDay(): String {
        quoteService = QuoteOfTheDayService()
        val qod = quoteService.getQuoteOfTheDay()
        val response = qod.execute()
        Log.e(TAG, "Response: $response")
        response?.let {
            quote = response.body()!!.contents.quotes[0].quote
            author = response.body()!!.contents.quotes[0].author
        }
        Log.e(TAG, "Expected Quote: $quote")
        return quote
    }

    companion object {
        private val TAG = InspirationalQuoteActivity::class.java.simpleName
    }
}
