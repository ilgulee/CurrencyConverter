package ilgulee.com.currencyconverter.data

import androidx.lifecycle.LiveData
import ilgulee.com.currencyconverter.database.DatabaseCurrencyConversion

interface LocalDataSource {
    fun getCurrenciesListFromDatabase(): LiveData<List<DatabaseCurrencyConversion>>
    suspend fun insertAll(currenciesList: List<DatabaseCurrencyConversion>)
    suspend fun update(id: String, exchangeRate: Double)
}