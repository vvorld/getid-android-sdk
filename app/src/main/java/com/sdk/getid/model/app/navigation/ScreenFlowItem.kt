package com.sdk.getid.model.app.navigation

//import com.sdk.getidlib.config.FlowScreens


/**
 * Created by Pavlo Kuchirka on 15-Oct-19.
 */
class ScreenFlowItem(
    val title: String,
//    val key: FlowScreens,
    var isActive: Boolean = false,
    val hasSubPref: Boolean = false
)
