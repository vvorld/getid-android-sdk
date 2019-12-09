package com.sdk.getid.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.sdk.getid.R
import com.sdk.getid.app.utils.ViewUtils
import com.sdk.getid.app.utils.gone
import com.sdk.getid.app.utils.hide
import com.sdk.getid.app.utils.show
import com.sdk.getid.presentation.activity.AppContract
import com.sdk.getid.ui.common.ActionBarMode
import com.sdk.getid.ui.common.ClickActionMenuListener
import com.sdk.getid.ui.common.CustomMenuIconMode
import com.sdk.getid.ui.global.BaseActivity
import com.sdk.getid.ui.listener.OnBackPressedListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.currentScope

class AppActivity : BaseActivity<AppContract.Presenter>(), AppContract.View {

    override val presenter: AppContract.Presenter by currentScope.inject()

    private var inputMethodManager: InputMethodManager? = null

    private var menuItem: MenuItem? = null

    private val isContentUnderToolbar: Boolean? = null

    private var customMenuIconMode: CustomMenuIconMode = CustomMenuIconMode.PLUS

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        presenter.view = this
        presenter.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)

        menuItem = menu?.findItem(R.id.action_custom)

        presenter.setFirstActionBarMode()

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val resId = when (customMenuIconMode) {
            CustomMenuIconMode.PLUS -> R.drawable.ic_add_white
            CustomMenuIconMode.DONE -> R.drawable.ic_done
        }

        menu?.findItem(R.id.action_custom)!!.icon = getDrawable(resId)

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        R.id.action_custom -> {
            presenter.onClickActionMenu()
            true
        }
        else -> super.onOptionsItemSelected(item!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (presenter.handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        supportFragmentManager.fragments.forEach {
            it.onActivityResult(
                requestCode,
                resultCode,
                data
            )
        }
    }

    override fun onBackPressed() {
        var backPressedListener: OnBackPressedListener? = null

        for (fragment in supportFragmentManager.fragments) {
            if (fragment is OnBackPressedListener) {
                backPressedListener = fragment
                break
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed()
            return
        }

        val countOfFragments = supportFragmentManager.backStackEntryCount
        presenter.backPressed(countOfFragments)
    }

    override fun showLoading() {
        progressBar?.show()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun changeToolbarMode(mode: ActionBarMode) {
        presenter.changeToolbarMode(mode)
    }

    override fun changeTitleToolbar(title: String) {
        toolbar?.title = title
    }

    override fun showKeyboard(v: View) {
        inputMethodManager?.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun hideKeyboard() {
        inputMethodManager?.apply {
            if (isActive) hideSoftInputFromWindow(rootLayout.windowToken, 0)
        }
    }

    override fun hideToolbarMode() {
        menuItem?.hide()
        toolbar?.hide()
    }

    override fun backToolbarMode() {
        menuItem?.hide()
        toolbar?.show()
    }

    override fun plusToolbarMode() {
        menuItem?.show()
        toolbar?.show()
        customMenuIconMode = CustomMenuIconMode.PLUS
    }

    override fun doneToolbarMode() {
        menuItem?.show()
        toolbar?.show()
        customMenuIconMode = CustomMenuIconMode.DONE
    }

    override fun onClickActionMenu() {
        for (fragment in supportFragmentManager.fragments.reversed()) {
            if (fragment is ClickActionMenuListener) fragment.onActionMenuPressed()
        }
    }

    override fun setupMainContainer(contentUnderToolbar: Boolean) {
        if (isContentUnderToolbar != null && isContentUnderToolbar == contentUnderToolbar) return

        val colorResId: Int = if (contentUnderToolbar) {
            setContainerUnderToolbar()
            android.R.color.transparent
        } else {
            setContainerBelowToolbar()
            R.color.colorPrimaryDark
        }

        setToolbarBackgroundColor(colorResId)
    }

    private fun init() {
        inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }


    private fun setContainerUnderToolbar() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(rootLayout)

        // FrameLayout
        constraintSet.connect(
            R.id.container,
            ConstraintSet.TOP,
            R.id.toolbar,
            ConstraintSet.BOTTOM,
            0
        )

        constraintSet.applyTo(rootLayout)
    }


    private fun setContainerBelowToolbar() {
        val statusBarHeight = ViewUtils.getStatusBarHeight()

        val constraintSet = ConstraintSet()
        val crossFade = rootLayout
        constraintSet.clone(crossFade)

        // Toolbar
        constraintSet.connect(
            R.id.toolbar, ConstraintSet.START,
            ConstraintSet.PARENT_ID, ConstraintSet.START,
            0
        )
        constraintSet.connect(
            R.id.toolbar, ConstraintSet.END,
            ConstraintSet.PARENT_ID, ConstraintSet.END,
            0
        )
        constraintSet.connect(
            R.id.toolbar, ConstraintSet.TOP,
            ConstraintSet.PARENT_ID, ConstraintSet.TOP,
            statusBarHeight
        )

        // FrameLayout
        val actionBarHeight = ViewUtils.getActionBarHeight()
        val frameLayoutMarginTop = actionBarHeight + statusBarHeight

        constraintSet.connect(
            R.id.container, ConstraintSet.START,
            ConstraintSet.PARENT_ID, ConstraintSet.START,
            0
        )
        constraintSet.connect(
            R.id.container, ConstraintSet.END,
            ConstraintSet.PARENT_ID, ConstraintSet.END,
            0
        )
        constraintSet.connect(
            R.id.container, ConstraintSet.TOP,
            R.id.toolbar, ConstraintSet.BOTTOM,
            frameLayoutMarginTop
        )

        constraintSet.applyTo(crossFade)
    }

    private fun setToolbarBackgroundColor(colorResId: Int) {
        val backgroundColor = ContextCompat.getColor(this, colorResId)
        toolbar?.setBackgroundColor(backgroundColor)
    }
}
