package com.sdk.getid.app.navigation

import com.sdk.getid.app.common.objects.Screens.GLOBAL_CONTAINER
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


/**
 * Created by ku4irka on 07-Oct-2019.
 */
object CiceroneNavigation {

    private fun cicerone(): Cicerone<Router> = CiceroneHolder.getCicerone(GLOBAL_CONTAINER)

    fun navigatorHolder(): NavigatorHolder = cicerone().navigatorHolder

    fun router(): Router = cicerone().router
}
