package ilgulee.com.currencyconverter.ui.screens.list

import android.app.Application
import androidx.lifecycle.*
import ilgulee.com.currencyconverter.domain.Currency
import ilgulee.com.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy {
        CurrencyRepository()
    }

    var originalCurrencyList = repository.currenciesList

    val input = MutableLiveData<String>()

    private val defaultSource = Currency("USD", 1.0, "United States Dollar")

    private val _source = MutableLiveData<Currency>()
    val source: LiveData<Currency> = _source

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        refreshCurrencyList()
        input.value = defaultSource.exchangeRate.toString()
        _source.value = defaultSource
    }


    private fun refreshCurrencyList() {
        coroutineScope.launch {
            repository.refreshCurrencyList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun changeSourceByClick(selectedCurrency: Currency) {
        _source.value = selectedCurrency
        input.value = selectedCurrency.exchangeRate.toString()
    }


    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CurrencyViewModel(
                    application
                ) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}

