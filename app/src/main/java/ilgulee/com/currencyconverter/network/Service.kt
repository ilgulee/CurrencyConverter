package ilgulee.com.currencyconverter.network

import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesProperty
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyLayerApi {
    @GET("live")
    fun getLiveExchangeRatesPropertyAsync(
        @Query("access_key") key: String,
        @Query("format") format: Int = 1
    ): Deferred<LiveExchangeRatesProperty>
}