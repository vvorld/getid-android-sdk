package com.sdk.getid.app.utils

import com.sdk.getid.app.AndroidApplication
import org.koin.android.ext.android.getKoin

/**
 * inject lazily given dependency
 *
 * @param name - bean name / optional
 */
inline fun <reified T> inject() = lazy { AndroidApplication.sInstance!!.getKoin().get<T>() }