package com.paparazziapps.securityapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.paparazziapps.securityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root;
        setContentView(view)


        showNetworkIntercept()


        showBannerAd()

        copyDataPortapeles()

    }

    private fun copyDataPortapeles() {

        binding.link.setOnClickListener {
            //Codigo para copiar al portapeles el link
            val texto = binding.textoYT.getText().toString()
            val clipboard = this.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("", texto)
            clipboard.setPrimaryClip(clip)

            //Mostrar mensaje para mostrar al usuario
            Toast.makeText(applicationContext, "Copiado! en portapapeles", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showBannerAd() {

        //Initialize adds
        MobileAds.initialize(this){}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.adView.adListener = object :AdListener()
        {

            override fun onAdClicked() {
                super.onAdClicked()

                Log.e("TAG","Probando Click en evento")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)

                Log.e("TAG", "Ad failed: " + p0)
            }
        }

    }

    private fun showNetworkIntercept() {

        binding.networkIntercept.setOnClickListener {
            val intent = Intent (this, NetworkInterceptActivity::class.java)
            startActivity(intent)
        }
    }


}