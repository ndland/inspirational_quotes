package me.nickland.inspirationalquote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import org.json.JSONObject
import java.net.URL

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
        return JSONObject(URL("https://quotes.rest/qod").readText())
                .getJSONObject("contents")
                .getJSONArray("quotes")
                .getJSONObject(0)
                .getString("quote")
    }
}
