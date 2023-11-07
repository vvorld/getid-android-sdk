package com.sdk.getid.app.di

import com.sdk.getid.model.app.network.RetrofitFactory
//import com.sdk.getidlib.model.data.service.JwtService
import org.koin.dsl.module

val networkModule = module {

    single {
        val retrofitFactory = RetrofitFactory()
//        retrofitFactory.initRetrofit().create(JwtService::class.java)
    }
}
