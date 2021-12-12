package com.example.booksfilter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var authorName = ""
    var countryName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Author = findViewById<TextInputLayout>(R.id.input1)
        val Country = findViewById<TextInputLayout>(R.id.input2)
        val Filter = findViewById<TextView>(R.id.applyfilter)

        val ResultCount = findViewById<TextView>(R.id.resulttotal)
        val Result1 = findViewById<TextView>(R.id.result1)
        val Result2 = findViewById<TextView>(R.id.result2)
        val Result3 = findViewById<TextView>(R.id.result3)

        Filter.setOnClickListener {
            val temp1=Author.editText?.text?.toString()
            if(!(temp1.isNullOrEmpty())) authorName = temp1
            val temp2=Country.editText?.text?.toString()
            if(!(temp2.isNullOrEmpty())) countryName = temp2


            val listOfFilteredBooks = mutableListOf<BookResult>()

            if(authorName.isNullOrEmpty() && countryName.isNullOrEmpty()){
                ResultCount.text = "Please fill one or more fields"
                Result1.text = ""
                Result2.text = ""
                Result3.text = ""
            }
            else{
                val myApp = application as MyApplication
                val httpApiService = myApp.httpApiService

                //run in network(IO) thread
                CoroutineScope(Dispatchers.IO).launch {
                    val jsonRes = httpApiService.getAllBooks()     //http req here

                    for (books in jsonRes){

                        if(countryName.isNullOrEmpty()){
                            if(books.author.equals(authorName)){
                                listOfFilteredBooks.add(books)
                            }
                        }

                        if(authorName.isNullOrEmpty()){
                            if(books.country.equals(countryName)){
                                listOfFilteredBooks.add(books)
                            }
                        }

                        if((books.author.equals(authorName)) && (books.country.equals(countryName))){
                            listOfFilteredBooks.add(books)
                        }

                    }

                    var count = listOfFilteredBooks.size

                    //find top 3 results
                    //run in gui(MAIN) thread
                    withContext(Dispatchers.Main){
                        ResultCount.text = "Results: $count"

                        if(count >= 3){
                            val name1 = listOfFilteredBooks.get(0).title
                            Result1.text = "Result: $name1"

                            val name2 = listOfFilteredBooks.get(1).title
                            Result2.text = "Result: $name2"

                            val name3 = listOfFilteredBooks.get(2).title
                            Result3.text = "Result: $name3"
                        }

                        if(count == 2){
                            val name1 = listOfFilteredBooks.get(0).title
                            Result1.text = "Result: $name1"

                            val name2 = listOfFilteredBooks.get(1).title
                            Result2.text = "Result: $name2"

                            Result3.text = ""
                        }

                        if(count == 1){
                            val name1 = listOfFilteredBooks.get(0).title
                            Result1.text = "Result: $name1"

                            Result2.text = ""
                            Result3.text = ""
                        }

                    }

                }

            }


        }

    }
}