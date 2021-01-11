package com.sdk.getid.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.sdk.getid.R
import com.sdk.getid.ui.java.JavaAppActivity
import com.sdk.getid.ui.kotlin.KotlinAppActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btn_kotlin.setOnClickListener {
            val myIntent = Intent(this@StartActivity, KotlinAppActivity::class.java)
            this@StartActivity.startActivity(myIntent)
        }

        btn_java.setOnClickListener {
            val myIntent = Intent(this@StartActivity, JavaAppActivity::class.java)
            this@StartActivity.startActivity(myIntent)
        }
    }
}