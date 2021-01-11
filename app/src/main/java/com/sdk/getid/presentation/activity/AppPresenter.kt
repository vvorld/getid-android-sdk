package com.sdk.getid.presentation.activity

import android.content.Intent
import com.sdk.getid.R
import com.sdk.getid.app.common.objects.Const.TWO_THOUSAND
import com.sdk.getid.app.common.objects.Screens
import com.sdk.getid.app.utils.getString
import com.sdk.getid.presentation.global.BasePresenterImpl
import com.sdk.getid.ui.kotlin.common.ActionBarMode

/**
 * Created by ku4irka on 08-Oct-2019.
 */

class AppPresenter : BasePresenterImpl<AppContract.View>(), AppContract.Presenter {

    override lateinit var view: AppContract.View

    override var flowScreen: String? = null

    private var backPressedTime: Long = 0

    private var barMode: ActionBarMode? = null
    private var isContentUnderToolbar: Boolean = false

    override fun onStart() = showFirstScreen()

    override fun backPressed(countOfFragments: Int) = if (countOfFragments == 1) {
        showMessageOrExit()
    } else {
        back()
    }

    override fun handleActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ): Boolean {
        val AUTH_REQUEST_CODE = 111
        return requestCode == AUTH_REQUEST_CODE
    }

    override fun setFirstActionBarMode() = changeMode()

    override fun changeToolbarMode(toolbarMode: ActionBarMode) {
        barMode = toolbarMode
        isContentUnderToolbar = true
        changeMode()
    }

    override fun onClickActionMenu() = view.onClickActionMenu()

    private fun showFirstScreen() = navigateTo(Screens.ChooseTypeFlow)

    private fun changeMode() {
        when (barMode) {
            ActionBarMode.HIDE -> view.hideToolbarMode()
            ActionBarMode.BACK -> view.backToolbarMode()
            ActionBarMode.PLUS -> view.plusToolbarMode()
            ActionBarMode.DONE -> view.doneToolbarMode()
        }
//        view.setupMainContainer(isContentUnderToolbar)
    }

    private fun showMessageOrExit() {
        val pressAgain = getString(R.string.pressAgain)
        if (backPressedTime + TWO_THOUSAND > System.currentTimeMillis()) {
            exit()
        } else {
            view.showToast(pressAgain)
        }
        backPressedTime = System.currentTimeMillis()
    }
}
