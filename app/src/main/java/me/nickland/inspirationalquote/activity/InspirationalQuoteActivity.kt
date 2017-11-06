package me.nickland.inspirationalquote.activity

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import me.nickland.inspirationalquote.R
import me.nickland.inspirationalquote.service.QuoteOfTheDayService

class InspirationalQuoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspirational_quote)
        val textView = findViewById<TextView>(R.id.inspirationalQuote) as TextView

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        textView.text = getQuoteOfTheDay()
    }

    private fun getQuoteOfTheDay(): String {
        return QuoteOfTheDayService().getQuoteOfTheDay()
    }

    companion object {
        private val TAG = InspirationalQuoteActivity::class.java.simpleName
    }
}
