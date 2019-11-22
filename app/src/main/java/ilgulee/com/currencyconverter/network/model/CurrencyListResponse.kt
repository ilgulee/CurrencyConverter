package ilgulee.com.currencyconverter.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ilgulee.com.currencyconverter.database.DatabaseCurrencyConversion

@JsonClass(generateAdapter = true)
data class CurrencyListResponse(
    @Json(name = "currencies")
    val currencies: Map<String, String> = mutableMapOf()
) {
    val currenciesList = currencies.toList()
}

fun CurrencyListResponse.asDatabaseModel(): List<DatabaseCurrencyConversion> {
    return currenciesList.map {
        DatabaseCurrencyConversion(symbol = it.first, unit = it.second)
    }
}

