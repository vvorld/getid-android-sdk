package com.sdk.getid.model.app.network

import com.sdk.getid.app.utils.AppSetupState
import com.sdk.getid.model.app.network.RequestUtilsOptions.getRequestHeadersWithApiKey
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFactory {

    private var synchronousRetrofit: Retrofit? = null
    private var retrofit: Retrofit? = null
    private var retrofitJwt: Retrofit? = null

    fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppSetupState.GET_ID_SDK_DOMAIN)
            .client(makeOkHttpClientWithApiKey())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun cleanInstances() {
        synchronousRetrofit = null
        retrofit = null
        retrofitJwt = null
    }

    private fun makeOkHttpClientWithApiKey() = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .addInterceptor(makeApiKeyInterceptor())
        .build()

    private fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun makeApiKeyInterceptor() = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder().apply { addHeadersWithApiKey() }

            val request = builder.build()
            return chain.proceed(request)
        }
    }

    private fun Request.Builder.addHeadersWithApiKey() = headers(getHeadersWithApiKey())

    private fun getHeadersWithApiKey() = Headers.Builder().apply {
        for (value in getRequestHeadersWithApiKey()) {
            set(value.first, value.second)
        }
    }.build()
}
