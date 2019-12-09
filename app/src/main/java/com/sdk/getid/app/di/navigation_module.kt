package com.sdk.getid.app.di

import com.sdk.getid.app.navigation.CiceroneNavigation
import com.sdk.getid.app.navigation.SupportFragmentNavigator
import org.koin.dsl.module


/**
 * Created by ku4irka on 07-Oct-2019.
 */
val navigationModule = module {
    single { CiceroneNavigation.navigatorHolder() }
    single { CiceroneNavigation.router() }

    factory { SupportFragmentNavigator() }
}
