package com.sdk.getid.presentation.global

import android.content.Context
import com.sdk.getid.ui.common.ActionBarMode

/**
 * Created by Pavlo Kuchirka on 09-Oct-19.
 */
interface BaseContract {

    interface View {

        val viewContext: Context?

        fun showLoading()

        fun hideLoading()

        fun showToast(message: String?)

        fun showToast(resID: Int)

        fun showLongToast(message: String?)

        fun showLongToast(resID: Int)

        fun showError(errorMessage: String?)

        fun changeToolbarMode(mode: ActionBarMode)

        fun changeTitleToolbar(title: String)

        fun showKeyboard(v: android.view.View)

        fun hideKeyboard()

        fun setAgreeButtonTitle(title: String)
    }

    interface Presenter<T> {

        var view: T

        fun onCreate() {}

        fun onStart()

        fun onStop()

        fun onClickTermsOfUse()

        fun onClickPrivacyPolicy()

        fun onClickAgree()

        fun initAgreeButtonTitle()
    }
}
