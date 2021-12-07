package com.example.guessinggamechangescreen

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val message1=intent.getStringExtra("message1")
        val message2=intent.getStringExtra("message2")

        findViewById<TextView>(R.id.result1).text="$message1"
        findViewById<TextView>(R.id.result2).text="$message2"

        if(message1=="You lost after 12 attempts.\n The number is"){
            findViewById<TextView>(R.id.result2).setTextColor(Color.parseColor("red"))
        }
        else{
            findViewById<TextView>(R.id.result2).setTextColor(Color.parseColor("green"))
        }


    }
}