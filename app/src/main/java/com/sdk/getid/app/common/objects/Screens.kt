package com.sdk.getid.app.common.objects

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.sdk.getid.ui.features.config.choose_type_flow.ChooseTypeFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


/**
 * Created by ku4irka on 07-Oct-2019.
 */

object Screens {

    const val GLOBAL_CONTAINER = "global_container"

    object ChooseTypeFlow : SupportAppScreen() {
        override fun getScreenKey() = "Choose Type Flow"
        override fun getFragment() = ChooseTypeFlowFragment()
    }

    // External fields
    data class ExternalBrowserFlow(private val url: String) : SupportAppScreen() {
        override fun getActivityIntent(context: Context?) =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}
