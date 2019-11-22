package ilgulee.com.currencyconverter.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ilgulee.com.currencyconverter.database.DatabaseCurrencyConversion

@JsonClass(generateAdapter = true)
data class LiveExchangeRatesResponse(
    @Json(name = "quotes")
    val quotes: Map<String, Double> = mutableMapOf()
) {
    val quotesList = quotes.toList()
}

fun LiveExchangeRatesResponse.asDatabaseModel(): List<DatabaseCurrencyConversion> {
    return quotesList.map {
        val symbol = it.first.substring(3..5)
        DatabaseCurrencyConversion(symbol = symbol, exchangeRate = it.second)
    }
}
