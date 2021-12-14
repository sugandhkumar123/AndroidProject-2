package com.example.eater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class signUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val message1=intent.getStringExtra("message1")
        val message2=intent.getStringExtra("message2")

        Log.d("Message1", ""+message1)
        Log.d("Message2", ""+message2)
    }
}