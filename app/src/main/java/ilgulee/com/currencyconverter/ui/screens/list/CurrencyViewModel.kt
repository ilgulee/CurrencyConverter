package ilgulee.com.currencyconverter.ui.screens.list

import android.app.Application
import androidx.lifecycle.*
import ilgulee.com.currencyconverter.app.CurrencyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {
    private val remoteDataSource by lazy {
        CurrencyApplication.getCurrencyApplication().currencyAppContainer.bindRemoteDataSource()
    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        getRemoteData()
    }

    private fun getRemoteData() {
        coroutineScope.launch {
            _response.value = remoteDataSource.getLiveExchangeRatesProperty().quotes.toString()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
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
