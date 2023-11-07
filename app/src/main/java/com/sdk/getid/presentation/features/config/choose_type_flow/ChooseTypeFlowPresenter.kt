package com.sdk.getid.presentation.features.config.choose_type_flow

import com.sdk.getid.R
import com.sdk.getid.app.AndroidApplication
import com.sdk.getid.app.common.objects.Screens
import com.sdk.getid.presentation.global.BasePresenterImpl
import com.sdk.getid.ui.kotlin.common.ActionBarMode
import com.sdk.getidlib.config.GetIDSDK
import com.sdk.getidlib.model.app.auth.Key

class ChooseTypeFlowPresenter : BasePresenterImpl<ChooseTypeFlowContract.View>(),
    ChooseTypeFlowContract.Presenter {

    override lateinit var view: ChooseTypeFlowContract.View

    override var flowScreen: String? = Screens.ChooseTypeFlow.screenKey


    private var currentSelectedTabsPos: Int = 0

    override fun onStart() {
        view.changeToolbarMode(ActionBarMode.HIDE)
        view.setAgreeButtonTitle(getStringRes(R.string.begin))
        super.onStart()
    }

    override fun onClickAgree() = startSdk()

    override fun initAgreeButtonTitle() =
        view.setAgreeButtonTitle(getStringRes(R.string.choose_flow))

    override fun changePagerTab(position: Int) {
        currentSelectedTabsPos = position
    }

    private fun startSdk() {
        GetIDSDK().startVerificationFlow(
            context = AndroidApplication.sInstance!!,
            apiUrl = "API_URL",
            auth = Key("SDK_KEY"),
            flowName = "FLOW_NAME"
        )
    }
}
