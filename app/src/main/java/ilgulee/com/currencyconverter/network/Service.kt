package ilgulee.com.currencyconverter.network

import ilgulee.com.currencyconverter.network.model.CurrencyListResponse
import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CurrencyLayerApi {

    @GET("live")
    fun getLiveExchangeRatesResponseAsync(
    ): Deferred<LiveExchangeRatesResponse>

    @GET("list")
    fun getCurrencyListResponseAsync(
    ): Deferred<CurrencyListResponse>
}