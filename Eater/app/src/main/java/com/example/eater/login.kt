package com.example.eater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel

//Log.d("mytag2","ram2");
//Log.d("mytag8",""+response.code());
//Log.d("mytag3",""+response.body()?.email);
//Log.d("mytag4",""+response.body()?.password);
//Log.d("mytag5",""+response.body()?.token);
//Log.d("mytag6",""+response.body()?.memberSince);
//Log.d("mytag7",""+response.body()?.id);



class login : AppCompatActivity() {

    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val buttonLogin =findViewById<Button>(R.id.buttonLogin)
        val buttonSignup =findViewById<Button>(R.id.buttonSignup)
        val inputEmail =findViewById<EditText>(R.id.inputEmail)
        val inputPassword =findViewById<EditText>(R.id.inputPassword)
        val progressBar =findViewById<ProgressBar>(R.id.progressBar)
        val viewOnProgress =findViewById<View>(R.id.viewOnProress)

//        val call = retroService.userLogin("user1@gmail.com","password1")

        buttonLogin.setOnClickListener {

            progressBar.setVisibility(View.VISIBLE)
            viewOnProgress.setVisibility(View.VISIBLE)
            buttonLogin.setVisibility(View.INVISIBLE)
            buttonSignup.setVisibility(View.INVISIBLE)
            email=inputEmail.text.toString()
            password=inputPassword.text.toString()

            // Create Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://android-kanini-course.cloud")
                .build()

            // Create Service
            val service = retrofit.create(Api::class.java)

            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()

            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            CoroutineScope(Dispatchers.IO).launch {
                // Do the POST request and get response
                val response = service.userLogin(requestBody)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        // Convert raw JSON to pretty JSON using GSON library
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(
                            JsonParser.parseString(
                                response.body()
                                   ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                            )
                        )
                        progressBar.setVisibility(View.INVISIBLE)
                        Log.d("Pretty Printed JSON :", prettyJson)
                        Log.d("Pretty Printed JSON :", prettyJson)
                        Toast.makeText(this@login, "Login Successfully", Toast.LENGTH_LONG).show()

//                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
//                        intent.putExtra("json_results", prettyJson)
//                        this@MainActivity.startActivity(intent)

                    } else {
                        buttonSignup.setVisibility(View.VISIBLE)
                        buttonLogin.setVisibility(View.VISIBLE)
                        progressBar.setVisibility(View.INVISIBLE)
                        viewOnProgress.setVisibility(View.INVISIBLE)
                        Log.e("RETROFIT_ERROR", response.code().toString())
                        Toast.makeText(this@login, "Invalid Credential", Toast.LENGTH_LONG).show()


                    }
                }
            }


        }
    }
}



