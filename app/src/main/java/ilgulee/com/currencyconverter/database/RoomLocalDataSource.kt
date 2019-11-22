package ilgulee.com.currencyconverter.database

import androidx.lifecycle.LiveData
import ilgulee.com.currencyconverter.app.CurrencyApplication
import ilgulee.com.currencyconverter.data.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomLocalDataSource : LocalDataSource {
    private val currencyAppContainer =
        CurrencyApplication.getCurrencyApplication().currencyAppContainer

    private val databaseDao = currencyAppContainer.provideDatabase().currencyDatabaseDao

    override fun getCurrenciesListFromDatabase(): LiveData<List<DatabaseCurrencyConversion>> {
        return databaseDao.getCurrencyConversion()
    }

    override suspend fun insertAll(currenciesList: List<DatabaseCurrencyConversion>) {
        withContext(Dispatchers.IO) {
            databaseDao.insertAll(currenciesList)
        }
    }

    override suspend fun update(id: String, exchangeRate: Double) {
        withContext(Dispatchers.IO) {
            databaseDao.update(id, exchangeRate)
        }
    }

}