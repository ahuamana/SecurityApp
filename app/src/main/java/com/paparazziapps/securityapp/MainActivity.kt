package com.paparazziapps.securityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paparazziapps.securityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityMainBinding.inflate(layoutInflater)
        val view = bindind.root;
        setContentView(view)


        showNetworkIntercept()


    }

    private fun showNetworkIntercept() {

        bindind.networkIntercept.setOnClickListener {
            val intent = Intent (this, NetworkInterceptActivity::class.java)
            startActivity(intent)
        }
    }


}