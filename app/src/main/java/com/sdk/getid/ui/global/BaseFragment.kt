package com.sdk.getid.ui.global

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdk.getid.presentation.activity.AppContract
import com.sdk.getid.presentation.global.BaseContract
import com.sdk.getid.ui.kotlin.common.ActionBarMode

abstract class BaseFragment<T : BaseContract.Presenter<*>> : Fragment(), BaseContract.View {

    private lateinit var baseViewAppView: AppContract.View

    abstract val layoutRes: Int
    abstract val presenter: T

    override val viewContext: Context? get() = context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewAppView = activity as AppContract.View
        presenter.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(layoutRes, container, false)

    override fun onPause() {
        super.onPause()
        baseViewAppView.hideLoading()
    }

    override fun onDestroyView() {
        presenter.onStop()
        super.onDestroyView()
    }

    override fun showLoading() {
        baseViewAppView.showLoading()
    }

    override fun hideLoading() {
        baseViewAppView.hideLoading()
    }

    override fun showToast(message: String?) {
        baseViewAppView.showToast(message)
    }

    override fun showToast(resID: Int) {
        baseViewAppView.showToast(resID)
    }

    override fun showLongToast(message: String?) {
        baseViewAppView.showLongToast(message)
    }

    override fun showLongToast(resID: Int) {
        baseViewAppView.showLongToast(resID)
    }

    override fun showError(errorMessage: String?) {
        baseViewAppView.showError(errorMessage)
    }

    override fun changeTitleToolbar(title: String) {
        baseViewAppView.changeTitleToolbar(title)
    }

    override fun changeToolbarMode(mode: ActionBarMode) {
        baseViewAppView.changeToolbarMode(mode)
    }

    override fun showKeyboard(v: View) {
        baseViewAppView.showKeyboard(v)
    }

    override fun hideKeyboard() {
        baseViewAppView.hideKeyboard()
    }

    override fun setAgreeButtonTitle(title: String) {}
}
