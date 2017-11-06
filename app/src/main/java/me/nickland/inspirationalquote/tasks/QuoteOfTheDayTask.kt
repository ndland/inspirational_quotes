package me.nickland.inspirationalquote.tasks

import android.os.AsyncTask
import me.nickland.inspirationalquote.service.QuoteOfTheDayService

/**
 * Created by nland on 11/6/17.
 */
class QuoteOfTheDayTask : AsyncTask<Void, Void, String>(){

    override fun doInBackground(vararg p0: Void?): String {
        return QuoteOfTheDayService().getQuoteOfTheDay()
    }
}