package me.nickland.inspirationalquote.service

import me.nickland.inspirationalquote.api.QuoteOfTheDayApi
import me.nickland.inspirationalquote.constants.Constants
import me.nickland.inspirationalquote.models.QuoteOfTheDayErrorResponse
import me.nickland.inspirationalquote.models.QuoteOfTheDayResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by hzf0pt on 11/1/2017.
 */
class QuoteOfTheDayService {

    private val quoteOfTheDayApi: QuoteOfTheDayApi
    private lateinit var retrofit: Retrofit
    private lateinit var message: String

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        quoteOfTheDayApi = retrofit.create(QuoteOfTheDayApi::class.java)
    }

    fun getQuoteOfTheDay(): String {
        return handleResponse(quoteOfTheDayApi.getQuoteOfTheDay())
    }

    fun handleResponse(quoteOfTheDay: Call<QuoteOfTheDayResponse>): String {
        val response = quoteOfTheDay.execute()
        if (response.isSuccessful) {
            response?.let {
                message = response.body()!!.contents.quotes[0].quote
            }
        } else {
            response?.let {
                val errorConverter = retrofit.responseBodyConverter<QuoteOfTheDayErrorResponse>(
                        QuoteOfTheDayErrorResponse::class.java,
                        arrayOfNulls<Annotation>(0))

                val errorResponse = errorConverter.convert(response.errorBody())
                message = errorResponse.error.message
            }
        }
        return message
    }

    companion object {
        private val TAG = QuoteOfTheDayService::class.java.simpleName
    }
}