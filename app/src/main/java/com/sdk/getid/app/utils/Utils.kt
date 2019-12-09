package com.sdk.getid.app.utils

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.sdk.getid.app.AndroidApplication

fun Context.getScreenWidth() = resources.displayMetrics.widthPixels

fun String.toRequestToken(): String = "Bearer $this"

fun String.firstLetterUppercase(): String {
    return if (this.isEmpty()) this
    else this.substring(0, 1).toUpperCase() + this.substring(1)
}

fun Context.isPermissionAllowed(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun getDrawable(@DrawableRes resId: Int) =
    ContextCompat.getDrawable(AndroidApplication.sInstance!!, resId)

fun showToast(resID: Int) {
    val message = getString(resID)
    showToast(message)
}

fun showLongToast(resID: Int) {
    val message = getString(resID)
    showLongToast(message)
}

fun showToast(message: String?) {
    if (message.isNullOrBlank()) return
    showToast(message, Toast.LENGTH_SHORT)
}


fun showLongToast(message: String?) {
    if (message.isNullOrBlank()) return
    showToast(message, Toast.LENGTH_LONG)
}

fun getString(@StringRes resId: Int) = AndroidApplication.sInstance!!.getString(resId)

private fun showToast(message: String, length: Int) =
    Toast.makeText(AndroidApplication.sInstance, message, length).show()
