package com.sdk.getid.presentation.activity

import android.content.Intent
import com.sdk.getid.presentation.global.BaseContract
import com.sdk.getid.ui.kotlin.common.ActionBarMode

interface AppContract {

    interface View : BaseContract.View {
        fun hideToolbarMode()
        fun backToolbarMode()
        fun plusToolbarMode()
        fun doneToolbarMode()

        fun onClickActionMenu()

        fun setupMainContainer(contentUnderToolbar: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun backPressed(countOfFragments: Int)

        fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean

        fun setFirstActionBarMode()
        fun changeToolbarMode(toolbarMode: ActionBarMode)
        fun onClickActionMenu()
    }
}
