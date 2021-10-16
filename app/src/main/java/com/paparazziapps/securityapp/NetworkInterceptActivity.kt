package com.paparazziapps.securityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.paparazziapps.securityapp.databinding.ActivityNetworkInterceptBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class NetworkInterceptActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkInterceptBinding

    var httpurl= "http://demo.testfire.net"
    var httpsurl= "https://owasp.org"//https://reqres.in/api/users?page=2"

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkInterceptBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.http.setOnClickListener {
            run(httpurl)
        }

        binding.https.setOnClickListener {
            run(httpsurl)
        }


    }


    fun run(url: String) {
        try {
            val request = Request.Builder()
                .url(url)
                .build()
            Toast.makeText(this, "Solicitud enviada "+url+" Porfavor intercepta el trafico usando un Proxy", Toast.LENGTH_LONG).show()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) = println(response.body?.string())
            })
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }//run

    fun DoPinning()
    {
        GlobalScope.launch {
            val url="owasp.org"
            try {
                val pinner1 = CertificatePinner.Builder()
                    .add(url, "sha256/gdU/UHClHJBFbIdeKuyHm/Lq/aQvMLyuTtcvTEE/1JQ=")
                    .add(url, "sha256/YLh1dUR9y6Kja30RrAn7JKnbQG/uEtLMkBgFF2Fuihg=")
                    .add(url, "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=")
                    .build()
                val client = OkHttpClient.Builder().certificatePinner(pinner1).build()
                val request = Request.Builder()
                    .url("https://"+url)
                    .build()
                //Toast.makeText(this@TrafficActivity, "Request Sent to https://"+url, Toast.LENGTH_LONG).show()
                val response = client.newCall(request).execute()
                Log.v("Response", response.body?.string().toString())

            } catch (e: Exception) {
                //Toast.makeText(this@TrafficActivity, "Pinning Verification Failed", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }
}