package ilgulee.com.currencyconverter.data

import ilgulee.com.currencyconverter.network.model.CurrencyListResponse
import ilgulee.com.currencyconverter.network.model.LiveExchangeRatesResponse

interface RemoteDataSource {
    suspend fun getLiveExchangeRatesProperty(): LiveExchangeRatesResponse
    suspend fun getCurrencyListResponse(): CurrencyListResponse
}