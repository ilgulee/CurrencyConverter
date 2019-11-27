package ilgulee.com.currencyconverter.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ilgulee.com.currencyconverter.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val KEY = "a1333d0832b1208792fdd9bd929adcf8"

class NetworkModule {

    companion object CurrencyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest: Request = chain.request()

            val newUrl: HttpUrl = originalRequest.url().newBuilder()
                .addQueryParameter("access_key", KEY)
                .addQueryParameter("format", "1")
                .build()

            val newRequest: Request = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(newRequest)
        }

    }

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://apilayer.net/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(provideLoggingInterceptor())
        httpClient.addInterceptor(CurrencyInterceptor)
        return httpClient.build()
    }

    fun provideCurrencyLayerApi(): CurrencyLayerApi {
        return provideRetrofit().create(CurrencyLayerApi::class.java)
    }
}