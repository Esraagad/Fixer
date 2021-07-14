package android.esraagad.com.fixer.view_model

import android.esraagad.com.fixer.data.network.APIBuilder
import android.esraagad.com.fixer.data.network.model.ExchangeRateResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val _response = MutableLiveData<ExchangeRateResponse>()
    val response: LiveData<ExchangeRateResponse>
        get() =
            _response

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        retrieveExchangeRateList()
    }

    private fun retrieveExchangeRateList() {
        //TODO:Move access key to shared preferences
        val restaurantsObservable: Observable<ExchangeRateResponse> =
            APIBuilder.exchangeRateApiClient.getExchangeRateList("2dd856ca08b05db4cea7ccee64992abc")
        restaurantsObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _response.value = it
            }, {
                _error.value = it.message
            })
    }
}