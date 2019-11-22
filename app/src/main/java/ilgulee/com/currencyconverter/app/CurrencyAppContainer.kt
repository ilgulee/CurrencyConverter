package ilgulee.com.currencyconverter.app

import ilgulee.com.currencyconverter.data.LocalDataSource
import ilgulee.com.currencyconverter.data.RemoteDataSource
import ilgulee.com.currencyconverter.database.CurrencyDatabase
import ilgulee.com.currencyconverter.database.RoomLocalDataSource
import ilgulee.com.currencyconverter.network.NetworkModule
import ilgulee.com.currencyconverter.network.NetworkRemoteDataSource

class CurrencyAppContainer {
    val networkModule = NetworkModule()
    fun provideDatabase(): CurrencyDatabase {
        return CurrencyDatabase.getInstance(CurrencyApplication.getContext())
    }
    fun bindRemoteDataSource(): RemoteDataSource = NetworkRemoteDataSource()
    fun bindLocalDataSource(): LocalDataSource = RoomLocalDataSource()

}