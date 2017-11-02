package me.nickland.inspirationalquote.service

import me.nickland.inspirationalquote.api.QuoteOfTheDayApi
import me.nickland.inspirationalquote.constants.Constants
import me.nickland.inspirationalquote.models.QuoteOfTheDayResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by hzf0pt on 11/1/2017.
 */
class QuoteOfTheDayService {

    private val quoteOfTheDayApi: QuoteOfTheDayApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        quoteOfTheDayApi = retrofit.create(QuoteOfTheDayApi::class.java)
    }

    fun getQuoteOfTheDay(): Call<QuoteOfTheDayResponse> {
        return quoteOfTheDayApi.getQuoteOfTheDay()
    }
}