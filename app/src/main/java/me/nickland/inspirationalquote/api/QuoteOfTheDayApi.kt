package me.nickland.inspirationalquote.api

import me.nickland.inspirationalquote.models.QuoteOfTheDayResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by hzf0pt on 11/1/2017.
 */
interface QuoteOfTheDayApi {

    @GET("/qod")
    fun getQuoteOfTheDay(): Call<QuoteOfTheDayResponse>
}