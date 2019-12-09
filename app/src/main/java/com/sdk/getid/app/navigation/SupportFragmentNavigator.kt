package com.sdk.getid.app.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sdk.getid.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command


/**
 * Created by ku4irka on 08-Oct-2019.
 */
class SupportFragmentNavigator {

    fun initNavigator(
        activity: FragmentActivity,
        fragmentManager: FragmentManager,
        navigatorListener: NavigatorListener?
    ) = object : SupportAppNavigator(activity, fragmentManager, R.id.container) {

        override fun createFragment(screen: SupportAppScreen?): Fragment {
            screen?.screenKey?.let {
                if (it.isNotBlank()) navigatorListener?.currentScreen(it)
            }

            return super.createFragment(screen)
        }

        override fun applyCommands(commands: Array<out Command>?) {
            super.applyCommands(commands)
            fragmentManager.executePendingTransactions()
        }

        override fun setupFragmentTransaction(
            command: Command?,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction?
        ) {
            super.setupFragmentTransaction(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
            )

            fragmentTransaction?.setCustomAnimations(
                R.animator.enter_from_end, R.animator.exit_to_start,
                R.animator.enter_from_start, R.animator.exit_to_end
            )
        }
    }
}
