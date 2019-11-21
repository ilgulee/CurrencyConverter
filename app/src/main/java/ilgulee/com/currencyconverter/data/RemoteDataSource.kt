package ilgulee.com.currencyconverter.data

import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesProperty

interface RemoteDataSource {
    suspend fun getLiveExchangeRatesProperty(): LiveExchangeRatesProperty
    //suspend fun getLiveExchangeRatesProperty(): String
}