package com.sdk.getid.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.sdk.getid.BuildConfig
import com.sdk.getid.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created by ku4irka on 07-Oct-2019.
 */
class AndroidApplication : Application() {

    companion object {
        var sInstance: AndroidApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        // Start Koin
        startKoin {
            androidContext(this@AndroidApplication)
            modules(appModule)
        }

//        if (!BuildConfig.DEBUG) {
//            // AppCenter
//            AppCenter.start(
//                this,
//                "87207324-bcf5-419b-96d8-eb84d3ccbd6f",
//                Analytics::class.java,
//                Crashes::class.java
//            )
//        }
    }
}
