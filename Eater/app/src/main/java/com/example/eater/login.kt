package com.example.eater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class login : AppCompatActivity() {

    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val buttonLogin =findViewById<Button>(R.id.buttonLogin)
        val inputEmail =findViewById<EditText>(R.id.inputEmail)
        val inputPassword =findViewById<EditText>(R.id.inputPassword)

        buttonLogin.setOnClickListener {
            Log.d("mytag0","ram0");
            email=inputEmail.text.toString().trim()
            password=inputPassword.text.toString().trim()

            RetrofitClient.instance.userLogin("user1@gmail.com","password1")
                .enqueue(object: Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        Log.d("mytag1","ram1");
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        Log.d("mytag2","ram2");
                        Log.d("mytag8",""+response.code());
                        Log.d("mytag3",""+response.body()?.email);
                        Log.d("mytag4",""+response.body()?.password);
                        Log.d("mytag5",""+response.body()?.token);
                        Log.d("mytag6",""+response.body()?.memberSince);
                        Log.d("mytag7",""+response.body()?.id);
//                        if(!response.body()?.error!!){
//
//                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)
//
//                            val intent = Intent(applicationContext, ProfileActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//                            startActivity(intent)
//
//
//                        }else{
//                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
//                        }

                    }
                })

        }
    }
}