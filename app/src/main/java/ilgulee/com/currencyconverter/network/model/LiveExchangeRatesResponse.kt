package ilgulee.com.currencyconverter.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiveExchangeRatesResponse(
    val privacy: String = "https://currencylayer.com/privacy",
    @Json(name = "quotes")
    val quotes: Map<String, Double> = mutableMapOf(),
    val source: String = "USD",
    val success: Boolean = false,
    val terms: String = "https://currencylayer.com/terms",
    val timestamp: Long = 0L
)
