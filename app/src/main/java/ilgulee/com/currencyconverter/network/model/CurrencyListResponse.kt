package ilgulee.com.currencyconverter.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyListResponse(
    @Json(name = "currencies")
    val currencies: Map<String, String> = mutableMapOf(),
    val privacy: String = "https://currencylayer.com/privacy",
    val success: Boolean = false,
    val terms: String = "https://currencylayer.com/terms"
)