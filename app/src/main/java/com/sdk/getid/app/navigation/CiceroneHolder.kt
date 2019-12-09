package com.sdk.getid.app.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import java.util.*


/**
 * Created by ku4irka on 07-Oct-2019.
 */
object CiceroneHolder {

    private var containers: HashMap<String, Cicerone<Router>> = hashMapOf()

    fun getCicerone(containerTag: String): Cicerone<Router> =
        containers.getOrPut(containerTag) { Cicerone.create(Router()) }
}
