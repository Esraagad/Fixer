package android.esraagad.com.fixer.data.network

import android.esraagad.com.fixer.data.network.client.ExchangeRateApiClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIBuilder {
    private const val HTTP_REQUEST_TIMEOUT = 30

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val mainInterceptor by lazy {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mainInterceptor)
            .connectTimeout(HTTP_REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(HTTP_REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://data.fixer.io/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val exchangeRateApiClient: ExchangeRateApiClient by lazy {
        retrofit.create(ExchangeRateApiClient::class.java)
    }
}