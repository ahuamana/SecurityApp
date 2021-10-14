package com.paparazziapps.securityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

import androidx.core.content.ContextCompat




class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //code before adding view
       // window.requestFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        window.statusBarColor = ContextCompat.getColor(this,R.color.blue_light)
        //
        setContentView(R.layout.activity_splash)

        showNewActivity()

    }
    private fun showNewActivity() {

        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }



}