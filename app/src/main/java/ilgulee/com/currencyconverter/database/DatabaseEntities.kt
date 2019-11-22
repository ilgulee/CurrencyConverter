package ilgulee.com.currencyconverter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ilgulee.com.currencyconverter.domain.Currency


@Entity(tableName = "currency_conversion_table")
data class DatabaseCurrencyConversion(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val symbol: String,
    val unit: String = "Any Country Money",
    val exchangeRate: Double = -1.0
)

fun List<DatabaseCurrencyConversion>.asCurrencyListDomain(): List<Currency> {
    return map {
        Currency(
            symbol = it.symbol,
            unit = it.unit,
            exchangeRate = it.exchangeRate
        )
    }
}