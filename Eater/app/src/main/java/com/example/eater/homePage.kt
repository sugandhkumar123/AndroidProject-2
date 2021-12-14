package com.example.eater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONObject

class homePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val message1=intent.getStringExtra("message1")
        val message2=intent.getStringExtra("message2")

        val userInfo = JSONObject(message2)
        val id = userInfo.getInt("id")
        val email = userInfo.getString("email")
        val token = userInfo.getString("token")
        val memberSince = userInfo.getLong("memberSince")


        Log.d("Message1", ""+message1)
        Log.d("Message2", ""+id)
        Log.d("Message3", ""+email)
        Log.d("Message4", ""+token)
        Log.d("Message5", ""+memberSince)
    }
}