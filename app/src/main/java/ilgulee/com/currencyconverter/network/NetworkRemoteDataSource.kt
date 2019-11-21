package ilgulee.com.currencyconverter.network

import ilgulee.com.currencyconverter.app.CurrencyApplication
import ilgulee.com.currencyconverter.data.RemoteDataSource
import ilgulee.com.currencyconverter.network.model.CurrencyListResponse
import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesResponse

private const val KEY = "c5ce13d3a60761a6b3b937b160ea07d8"

class NetworkRemoteDataSource : RemoteDataSource {

    private val currencyAppContainer =
        CurrencyApplication.getCurrencyApplication().currencyAppContainer

    private val api = currencyAppContainer.networkModule.provideCurrencyLayerApi()

    override suspend fun getLiveExchangeRatesProperty(): LiveExchangeRatesResponse {
        return api.getLiveExchangeRatesResponseAsync(KEY).await()
    }

    override suspend fun getCurrencyListResponse(): CurrencyListResponse {
        return api.getCurrencyListResponseAsync(KEY).await()
    }

}