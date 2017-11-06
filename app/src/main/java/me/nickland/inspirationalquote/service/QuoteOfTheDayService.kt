package me.nickland.inspirationalquote.service

import me.nickland.inspirationalquote.api.QuoteOfTheDayApi
import me.nickland.inspirationalquote.constants.Constants
import me.nickland.inspirationalquote.models.QuoteOfTheDayErrorResponse
import me.nickland.inspirationalquote.models.QuoteOfTheDayResponse
import retrofit2.Call
import retrofit2.Response
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
        return extractMessageFromResponse(quoteOfTheDayApi.getQuoteOfTheDay())
    }

    private fun extractMessageFromResponse(quoteOfTheDay: Call<QuoteOfTheDayResponse>): String {
        val response = preformGetRequestOnQuoteOfTheDay(quoteOfTheDay)
        if (response.isSuccessful) {
            extractQuoteOfTheDay(response)
        } else {
            convertResponseToErrorResponse(response)
        }
        return message
    }

    private fun preformGetRequestOnQuoteOfTheDay(quoteOfTheDay: Call<QuoteOfTheDayResponse>): Response<QuoteOfTheDayResponse> {
        return quoteOfTheDay.execute()
    }

    private fun convertResponseToErrorResponse(response: Response<QuoteOfTheDayResponse>) {
        response.let {
            val errorConverter = retrofit.responseBodyConverter<QuoteOfTheDayErrorResponse>(
                    QuoteOfTheDayErrorResponse::class.java,
                    arrayOfNulls<Annotation>(0))

            extractErrorMessage(errorConverter.convert(response.errorBody()))
        }
    }

    private fun extractErrorMessage(errorResponse: QuoteOfTheDayErrorResponse) {
        errorResponse.let {
            message = errorResponse.error.message
        }
    }

    private fun extractQuoteOfTheDay(response: Response<QuoteOfTheDayResponse>) {
        response.let {
            message = response.body()
                    ?.contents
                    ?.quotes
                    ?.get(0)
                    ?.quote!!
        }
    }

    companion object {
        private val TAG = QuoteOfTheDayService::class.java.simpleName
    }
}