package com.sdk.getid.presentation.features.config.choose_type_flow

import com.sdk.getid.model.app.flow.TypeFlow
import com.sdk.getid.presentation.global.BaseContract

/**
 * Created by Pavlo Kuchirka on 01-Nov-19.
 */
interface ChooseTypeFlowContract {

    interface View : BaseContract.View {
        fun showTypeOfFlows(flows: ArrayList<TypeFlow>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun changePagerTab(position: Int)
    }
}
