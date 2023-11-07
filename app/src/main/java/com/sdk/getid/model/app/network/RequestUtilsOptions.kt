package com.sdk.getid.model.app.network

import com.sdk.getid.app.utils.AppSetupState
//import com.sdk.getidlib.model.data.service.ServiceParams.API_KEY


object RequestUtilsOptions {

    private const val ACCEPT = "Accept"
    private const val CONTENT_TYPE = "Content-type"
    private const val APPLICATION_JSON = "application/json"

    fun getRequestHeadersWithApiKey() = arrayListOf<Pair<String, String>>().apply {
        add(Pair(ACCEPT, APPLICATION_JSON))
        add(Pair(CONTENT_TYPE, APPLICATION_JSON))
//        add(Pair(API_KEY, AppSetupState.GET_ID_API_KEY))
    }
}
