package com.sdk.getid.app.di

import com.sdk.getid.app.navigation.CiceroneNavigation
import com.sdk.getid.app.navigation.SupportFragmentNavigator
import org.koin.dsl.module


val navigationModule = module {
    single { CiceroneNavigation.navigatorHolder() }
    single { CiceroneNavigation.router() }

    factory { SupportFragmentNavigator() }
}
