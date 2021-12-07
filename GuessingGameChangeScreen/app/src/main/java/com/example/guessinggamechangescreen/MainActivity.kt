package com.example.guessinggamechangescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    var chanceVar=12
    var randomVal= (((Math.random()*26)/5)*21).toInt()
//    var randomVal= 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var inputView=findViewById<TextInputLayout>(R.id.inputfield)
        var clickMeButton=findViewById<TextView>(R.id.clickme)
        var displayme=findViewById<TextView>(R.id.displayview)
        var displaychance=findViewById<TextView>(R.id.chanceview)

        clickMeButton.setOnClickListener{
            val getText = inputView.editText?.text?.toString()
            if(getText.isNullOrEmpty())  displayme.text="Please Enter some Number"
            else if(getText.toInt() is Int) {
                val tempVal=getText.toInt()
                if ( tempVal> randomVal) {
                    displayme.text = "No:) My number is Smaller"
                    chanceVar--
                    if(chanceVar<=0)
                    {
                        val newScreenIntent= Intent(this,result::class.java)
                        newScreenIntent.putExtra("message1","You lost after 12 attempts.\n The number is")
                        newScreenIntent.putExtra("message2","$randomVal")
                        startActivity(newScreenIntent)
                    }
                    else displaychance.text="You have $chanceVar  chance left"
                }
                else if (tempVal < randomVal) {
                    displayme.text = "No:) My number is Bigger"
                    chanceVar--
                    if(chanceVar<=0)
                    {
                        val newScreenIntent= Intent(this,result::class.java)
                        newScreenIntent.putExtra("message1","You lost after 12 attempts.\n The number is")
                        newScreenIntent.putExtra("message2","$randomVal")
                        startActivity(newScreenIntent)
                    }
                    else displaychance.text="You have $chanceVar  chance left"
                }
                else {
//                  displayme.text = "You are Right!!"
                    displayme.text = ""
                    val newScreenIntent= Intent(this,result::class.java)
                    newScreenIntent.putExtra("message1","You won! The number is")
                    newScreenIntent.putExtra("message2","$randomVal")
                    startActivity(newScreenIntent)
                    displaychance.text=""
                }
            }
            else {
                displayme.text="Please Enter Valid Number"
            }

        }
    }
}