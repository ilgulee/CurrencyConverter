package ilgulee.com.currencyconverter.app

import android.app.Application
import android.content.Context

class CurrencyApplication : Application() {

    val currencyAppContainer = CurrencyAppContainer()

    companion object {
        private lateinit var instance: CurrencyApplication
        fun getContext(): Context = instance.applicationContext
        fun getCurrencyApplication(): CurrencyApplication = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}