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
    val originalCurrencyList = repository.currenciesList

    private lateinit var viewModelReference: List<Currency>
    private lateinit var listDataForCalculation: List<Currency>
    private val _adapterData = MutableLiveData<List<Currency>>()
    val adapterData: LiveData<List<Currency>> = _adapterData

    val input = MutableLiveData<String>()

    private var defaultSource: Currency
    private val _source = MutableLiveData<Currency>()
    val source: LiveData<Currency> = _source

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        refreshCurrencyList()

        defaultSource = Currency("USD", 0.0, "United States Dollar")
        input.value = defaultSource.exchangeRate.toString()
    }


    private fun refreshCurrencyList() {
        coroutineScope.launch {
            try {
                repository.refreshCurrencyList()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                if (originalCurrencyList.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }

        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun changeSourceByClick(selectedCurrency: Currency) {

        val symbol = selectedCurrency.symbol
        val currency = viewModelReference.first {
            it.symbol == symbol
        }
        defaultSource = currency
        val divider = currency.exchangeRate
        input.value = divider?.let { currency.exchangeRate!!.div(it).toString() }
        _source.value = currency
        _adapterData.value =
            divider?.let { viewModelReference.asCalculatedCurrenciesList(divider = it) }
        listDataForCalculation = _adapterData.value!!
    }

    fun setDataInsideViewModel(list: List<Currency>) {
        _source.value = defaultSource
        _adapterData.value = list
        viewModelReference = list
        listDataForCalculation = list
    }

    fun calculateCurrenciesList(times: Double) {
        _adapterData.value = listDataForCalculation.asTimesCurrenciesList(times)
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

fun List<Currency>.asCalculatedCurrenciesList(divider: Double): List<Currency> {
    return map {
        Currency(
            symbol = it.symbol,
            exchangeRate = it.exchangeRate?.div(divider),
            unit = it.unit
        )
    }
}

fun List<Currency>.asTimesCurrenciesList(times: Double): List<Currency> {
    return map {
        Currency(
            symbol = it.symbol,
            exchangeRate = it.exchangeRate?.times(times),
            unit = it.unit
        )
    }
}