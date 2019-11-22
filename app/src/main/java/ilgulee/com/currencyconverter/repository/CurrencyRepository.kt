package ilgulee.com.currencyconverter.repository

import androidx.lifecycle.Transformations
import ilgulee.com.currencyconverter.app.CurrencyApplication
import ilgulee.com.currencyconverter.database.asCurrencyListDomain
import ilgulee.com.currencyconverter.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepository {
    private val currencyAppContainer =
        CurrencyApplication.getCurrencyApplication().currencyAppContainer
    private val localDataSource = currencyAppContainer.bindLocalDataSource()
    private val remoteDataSource = currencyAppContainer.bindRemoteDataSource()

    val currenciesList = Transformations
        .map(localDataSource.getCurrenciesListFromDatabase()) {
            it?.asCurrencyListDomain()
        }

    suspend fun refreshCurrencyList() {
        withContext(Dispatchers.IO) {

            localDataSource.insertAll(remoteDataSource.getCurrencyListResponse().asDatabaseModel())

            remoteDataSource.getLiveExchangeRatesProperty().asDatabaseModel().forEach {
                localDataSource.update(it.symbol, it.exchangeRate)
            }
        }
    }
}