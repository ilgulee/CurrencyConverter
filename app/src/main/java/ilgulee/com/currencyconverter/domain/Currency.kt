package ilgulee.com.currencyconverter.domain

data class Currency(
    val symbol: String,
    val exchangeRate: Double?,
    val unit: String?
)