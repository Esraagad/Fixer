package android.esraagad.com.fixer.data.network.client

import android.esraagad.com.fixer.data.network.model.ExchangeRateResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApiClient {

    @GET("latest")
    fun getExchangeRateList(@Query("access_key") accessKey: String): Observable<ExchangeRateResponse>
}