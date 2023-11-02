package com.sdk.getid.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.sdk.getid.R
import com.sdk.getid.databinding.ActivityStartBinding
import com.sdk.getid.ui.java.JavaAppActivity
import com.sdk.getid.ui.kotlin.KotlinAppActivity


class StartActivity : Activity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnKotlin.setOnClickListener {
            val myIntent = Intent(this@StartActivity, KotlinAppActivity::class.java)
            this@StartActivity.startActivity(myIntent)
        }

        binding.btnJava.setOnClickListener {
            val myIntent = Intent(this@StartActivity, JavaAppActivity::class.java)
            this@StartActivity.startActivity(myIntent)
        }
    }
}