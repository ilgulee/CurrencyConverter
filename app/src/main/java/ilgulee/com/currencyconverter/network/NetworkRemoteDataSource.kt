package ilgulee.com.currencyconverter.network

import ilgulee.com.currencyconverter.app.CurrencyApplication
import ilgulee.com.currencyconverter.data.RemoteDataSource
import ilgulee.com.currencyconverter.network.model.CurrencyListResponse
import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesResponse

class NetworkRemoteDataSource : RemoteDataSource {

    private val currencyAppContainer =
        CurrencyApplication.getCurrencyApplication().currencyAppContainer

    private val api = currencyAppContainer.networkModule.provideCurrencyLayerApi()

    override suspend fun getLiveExchangeRatesProperty(): LiveExchangeRatesResponse {
        return api.getLiveExchangeRatesResponseAsync().await()
    }

    override suspend fun getCurrencyListResponse(): CurrencyListResponse {
        return api.getCurrencyListResponseAsync().await()
    }

}