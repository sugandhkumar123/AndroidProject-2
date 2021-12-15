package com.example.eater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class landingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)


        val runnable = Runnable {
            val landingIntent = Intent(this@landingPage, login::class.java)
            this@landingPage.startActivity(landingIntent)
        }
        val handler = Handler()
        handler.postDelayed(runnable, 1000)

    }
}