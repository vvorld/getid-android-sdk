package com.sdk.getid.presentation.global

import com.sdk.getid.R
import com.sdk.getid.app.common.objects.Screens
import com.sdk.getid.app.utils.inject
import com.sdk.getid.model.app.flow.FlowScreenPositionState
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

abstract class BasePresenterImpl<T : BaseContract.View> : BaseContract.Presenter<T> {

    companion object {
        const val TERMS_OF_USE_URL = "https://getid.ee/sdk-terms-of-use/"
        const val PRIVACY_POLICY_URL = "https://getid.ee/sdk-privacy-policy/"
    }

    lateinit var stateButtonAgree: FlowScreenPositionState

    abstract var flowScreen: String?

    private val router: Router by inject()

    override fun onStart() {
//        AppFlowState.currentFlowScreenName = flowScreen
//        stateButtonAgree = router.getAgreeButtonState()
//        initAgreeButtonTitle()
    }

    override fun onStop() {
//        clearAllNetworkSubscribers()
        view.hideKeyboard()
    }

    override fun onClickTermsOfUse() = openWebView(TERMS_OF_USE_URL)

    override fun onClickPrivacyPolicy() = openWebView(PRIVACY_POLICY_URL)

    override fun onClickAgree() {
        view.hideKeyboard()
        view.showToast("onClickAgree")
//        if (isSubmitDataScreen()) {
//            navigateToLoadingScreen()
//        } else {
//            navigateToNextFlowScreen()
//        }
    }

    override fun initAgreeButtonTitle() {
        val titleRes = when (stateButtonAgree) {
            FlowScreenPositionState.NOT_INITIALIZED -> R.string.start
            FlowScreenPositionState.EMPTY_FLOW -> R.string.start
            FlowScreenPositionState.CURRENT_LAST -> R.string.done
            FlowScreenPositionState.NEXT_LAST -> R.string.submit
            FlowScreenPositionState.NEXT_IS_NOT_LAST -> R.string.next
        }

        view.setAgreeButtonTitle(getStringRes(titleRes))
    }

    protected fun openWebView(url: String) = navigateTo(Screens.ExternalBrowserFlow(url))

    protected fun back() = router.exit()

    protected fun navigateTo(screen: Screen) = router.navigateTo(screen)

    protected fun newRootScreen(screen: Screen) = router.newRootScreen(screen)

    protected fun replaceScreen(screen: Screen) = router.replaceScreen(screen)

    protected fun openNewPreviousScreen(screen: Screen) {
        backToScreen(screen)
        router.replaceScreen(screen)
    }

    protected fun backToScreen(screen: Screen) = router.backTo(screen)

    protected fun exit() = router.finishChain()

    protected fun getStringRes(resource: Int) =
        view.viewContext?.resources?.getString(resource).toString()

}
