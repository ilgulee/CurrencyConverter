package ilgulee.com.currencyconverter.network

import ilgulee.com.currencyconverter.app.CurrencyApplication
import ilgulee.com.currencyconverter.data.RemoteDataSource
import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesProperty

private const val KEY = "c5ce13d3a60761a6b3b937b160ea07d8"

class NetworkRemoteDataSource : RemoteDataSource {
    private val currencyAppContainer =
        CurrencyApplication.getCurrencyApplication().currencyAppContainer
    private val api = currencyAppContainer.networkModule.provideCurrencyLayerApi()
    override suspend fun getLiveExchangeRatesProperty(): LiveExchangeRatesProperty {
        return api.getLiveExchangeRatesPropertyAsync(KEY).await()
    }
//    override suspend fun getLiveExchangeRatesProperty(): String {
//        return api.getLiveExchangeRatesPropertyAsync(KEY).await()
//    }
}