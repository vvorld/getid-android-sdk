package com.sdk.getid.app.utils

import android.content.Context
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.View.*
import com.bumptech.glide.Glide
import com.sdk.getid.app.AndroidApplication

object ViewUtils {
    fun getStatusBarHeight(): Int {
        var statusBarHeight = 0
        AndroidApplication.sInstance?.resources?.apply {
            val resourceId = getIdentifier("status_bar_height", "dimen", "android")
            statusBarHeight = if (resourceId > 0) {
                getDimensionPixelSize(resourceId)
            } else 0
        }

        return statusBarHeight
    }

    fun getActionBarHeight(): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        AndroidApplication.sInstance?.apply {
            val isResolveAttribute =
                theme?.resolveAttribute(android.R.attr.actionBarSize, tv, true) ?: false
            if (isResolveAttribute) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, resources.displayMetrics
                )
            }
        }

        return actionBarHeight
    }
}

fun View.setVisibility(isVisible: Boolean, invisibleGone: Boolean = false) {
    if (isVisible) show()
    else {
        if (invisibleGone) gone()
        else hide()
    }
}

fun View.show() {
    if (visibility != VISIBLE) visibility = VISIBLE
}

fun View.hide() {
    if (visibility != INVISIBLE) visibility = INVISIBLE
}

fun View.gone() {
    if (visibility != GONE) visibility = GONE
}

fun View.isVisible() = visibility == VISIBLE

fun MenuItem.show() {
    isVisible = true
}

fun MenuItem.hide() {
    isVisible = false
}

fun View.clearGlideLoading() {
    Glide.with(this).clear(this)
}

fun Context.dpSize(size: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    size,
    resources.displayMetrics
)