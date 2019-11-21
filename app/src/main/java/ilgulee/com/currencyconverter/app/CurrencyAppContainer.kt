package ilgulee.com.currencyconverter.app

import ilgulee.com.currencyconverter.data.RemoteDataSource
import ilgulee.com.currencyconverter.network.NetworkModule
import ilgulee.com.currencyconverter.network.NetworkRemoteDataSource

class CurrencyAppContainer {
    val networkModule = NetworkModule()
    fun bindRemoteDataSource(): RemoteDataSource = NetworkRemoteDataSource()
}