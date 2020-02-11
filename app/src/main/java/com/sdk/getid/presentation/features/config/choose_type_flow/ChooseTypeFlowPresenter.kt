package com.sdk.getid.presentation.features.config.choose_type_flow

import com.sdk.getid.R
import com.sdk.getid.app.AndroidApplication
import com.sdk.getid.app.common.objects.Screens
import com.sdk.getid.model.app.flow.TypeFlow
import com.sdk.getid.model.app.navigation.ScreenFlowItem
import com.sdk.getid.presentation.global.BasePresenterImpl
import com.sdk.getid.ui.common.ActionBarMode
import com.sdk.getidlib.config.ConfigurationPreset
import com.sdk.getidlib.config.FlowScreens
import com.sdk.getidlib.config.GetIDFactory

/**
 * Created by Pavlo Kuchirka on 01-Nov-19.
 */
class ChooseTypeFlowPresenter : BasePresenterImpl<ChooseTypeFlowContract.View>(),
    ChooseTypeFlowContract.Presenter {

    override lateinit var view: ChooseTypeFlowContract.View

    override var flowScreen: String? = Screens.ChooseTypeFlow.screenKey

    private var listOfTypeOfFlows = emptyList<TypeFlow>()

    private var currentSelectedTabsPos: Int = 0

    override fun onStart() {
        view.changeToolbarMode(ActionBarMode.HIDE)
        view.setAgreeButtonTitle(getStringRes(R.string.begin))
        initTypeOfFlows()
        super.onStart()
    }

    override fun onClickAgree() = startSdk()

    override fun initAgreeButtonTitle() =
        view.setAgreeButtonTitle(getStringRes(R.string.choose_flow))

    override fun changePagerTab(position: Int) {
        currentSelectedTabsPos = position
    }

    private fun initTypeOfFlows() {
        listOfTypeOfFlows = arrayListOf(
            TypeFlow(
                R.drawable.ic_document_photo,
                R.string.document_and_photo,
                arrayListOf(
                    ScreenFlowItem("Consent", FlowScreens.SCREEN_CONSENT),
                    ScreenFlowItem("Form", FlowScreens.SCREEN_FORM, hasSubPref = true),
                    ScreenFlowItem("Document", FlowScreens.SCREEN_DOCUMENT),
                    ScreenFlowItem("Selfie", FlowScreens.SCREEN_SELFIE),
                    ScreenFlowItem("Thank you", FlowScreens.SCREEN_THANKS)
                )
            ),
            TypeFlow(
                R.drawable.ic_document_only,
                R.string.document_only,
                arrayListOf(
                    ScreenFlowItem("Consent", FlowScreens.SCREEN_CONSENT),
                    ScreenFlowItem("Form", FlowScreens.SCREEN_FORM, hasSubPref = true),
                    ScreenFlowItem("Document", FlowScreens.SCREEN_DOCUMENT),
                    ScreenFlowItem("Thank you", FlowScreens.SCREEN_THANKS)
                )
            )
        )
        view.showTypeOfFlows(listOfTypeOfFlows as ArrayList<TypeFlow>)
    }

    private fun startSdk() {
        val application = AndroidApplication.sInstance!!

        val apiKey = "API_KEY"
        val url = "DOMAIN_URL"

        val configuration = getConfiguration()

        GetIDFactory().setup(application, apiKey, url, configuration)
    }


    private fun getConfiguration() = ConfigurationPreset().apply {
        flowItems = getFlowItems()
    }

    private fun getFlowItems(): ArrayList<FlowScreens> {
        val flowList = arrayListOf<FlowScreens>()
        for (item in listOfTypeOfFlows[currentSelectedTabsPos].flowItems) {
            flowList.add(item.key)
        }

        return flowList
    }
}
