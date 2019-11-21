package ilgulee.com.currencyconverter.network

import ilgulee.com.currencyconverter.network.model.CurrencyListResponse
import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyLayerApi {

    @GET("live")
    fun getLiveExchangeRatesResponseAsync(
        @Query("access_key") key: String,
        @Query("format") format: Int = 1
    ): Deferred<LiveExchangeRatesResponse>

    @GET("list")
    fun getCurrencyListResponseAsync(
        @Query("access_key") key: String,
        @Query("format") format: Int = 1
    ): Deferred<CurrencyListResponse>
}