package com.sdk.getid.app.di

import com.sdk.getid.presentation.activity.AppContract
import com.sdk.getid.presentation.activity.AppPresenter
import com.sdk.getid.presentation.features.config.choose_type_flow.ChooseTypeFlowContract
import com.sdk.getid.presentation.features.config.choose_type_flow.ChooseTypeFlowPresenter
import com.sdk.getid.ui.activity.AppActivity
import com.sdk.getid.ui.features.config.choose_type_flow.ChooseTypeFlowFragment
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by ku4irka on 08-Oct-2019.
 */
val presentationModule = module {

    scope(named<AppActivity>()) { scoped<AppContract.Presenter> { AppPresenter() } }

    // Config Screen
    scope(named<ChooseTypeFlowFragment>()) {
        scoped<ChooseTypeFlowContract.Presenter> { ChooseTypeFlowPresenter() }
    }
}
