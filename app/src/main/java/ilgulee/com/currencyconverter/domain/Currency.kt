package ilgulee.com.currencyconverter.domain

data class Currency(
    val symbol: String,
    var exchangeRate: Double?,
    val unit: String?
)