package me.nickland.inspirationalquote.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import me.nickland.inspirationalquote.R
import me.nickland.inspirationalquote.tasks.QuoteOfTheDayTask

class InspirationalQuoteActivity : AppCompatActivity() {

    private lateinit var quoteText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspirational_quote)
        val textView = findViewById<TextView>(R.id.inspirationalQuote) as TextView

        Log.i(TAG, "Message: $quoteText")
        Log.i(TAG, "About to call the API")
        quoteText = QuoteOfTheDayTask().execute().get()
        textView.text = quoteText
        Log.i(TAG, "Finished calling the API")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.i(TAG, "Outstate: $outState")
        Log.i(TAG, "Saving already retrieved quote")
        outState?.putString("Quote", quoteText)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private val TAG = InspirationalQuoteActivity::class.java.simpleName
    }
}
