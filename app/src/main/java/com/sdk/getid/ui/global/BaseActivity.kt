package com.sdk.getid.ui.global

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sdk.getid.app.navigation.NavigatorListener
import com.sdk.getid.app.navigation.SupportFragmentNavigator
import com.sdk.getid.presentation.global.BaseContract
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

abstract class BaseActivity<T : BaseContract.Presenter<*>> : AppCompatActivity(),
    BaseContract.View, NavigatorListener {

    companion object {
        private val TAG = BaseActivity::class.java.simpleName

        private const val ERROR_DIALOG_TAG = "error_dialog"
    }

    private val navigatorHolder: NavigatorHolder by inject()

    private lateinit var fragmentNavigator: Navigator

    override val viewContext: Context? get() = this

    abstract val presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navigator: SupportFragmentNavigator = get()
        fragmentNavigator = navigator.initNavigator(this, supportFragmentManager, this)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(fragmentNavigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onStop()
        super.onDestroy()
    }

    override fun showError(errorMessage: String?) = showToast(errorMessage)

    override fun showToast(message: String?) {
        if (message.isNullOrBlank()) return
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(resID: Int) {
        val message = getString(resID)
        showToast(message)
    }

    override fun showLongToast(message: String?) {
        if (message.isNullOrBlank()) return
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLongToast(resID: Int) {
        val message = getString(resID)
        showLongToast(message)
    }

    override fun currentScreen(screenKey: String) {
//        presenter.currentScreen(screenKey)
    }

    override fun setAgreeButtonTitle(title: String) {}
}
