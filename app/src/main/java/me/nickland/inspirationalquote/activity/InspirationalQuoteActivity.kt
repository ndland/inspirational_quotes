package me.nickland.inspirationalquote.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import me.nickland.inspirationalquote.R
import me.nickland.inspirationalquote.tasks.QuoteOfTheDayTask

class InspirationalQuoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspirational_quote)
        val textView = findViewById<TextView>(R.id.inspirationalQuote) as TextView

        textView.text = QuoteOfTheDayTask().execute().get()
    }

    companion object {
        private val TAG = InspirationalQuoteActivity::class.java.simpleName
    }
}
