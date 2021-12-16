package com.example.eater

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import org.json.JSONObject

class homePage : AppCompatActivity() {

    lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        val buttonLogout =findViewById<Button>(R.id.logout)
        sharedPreference =  getSharedPreferences("SaveData_Locally", Context.MODE_PRIVATE)

        buttonLogout.setOnClickListener {
            var editor:SharedPreferences.Editor = sharedPreference.edit()
            editor.clear()
            editor.commit()
            Toast.makeText(this@homePage, "Logout Successfully", Toast.LENGTH_LONG).show()
            val loginIntent3= Intent(this,landingPage::class.java)
            this.startActivity(loginIntent3)
//            finish()
        }
    }
}