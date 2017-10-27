package me.nickland.inspirationalquote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class InspirationalQuoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspirational_quote)
        val textView = findViewById<TextView>(R.id.inspirationalQuote) as TextView

        textView.text = getString(R.string.inspirationalQuote)
    }
}
