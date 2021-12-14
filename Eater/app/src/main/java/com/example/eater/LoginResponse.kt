package com.example.eater

data class LoginResponse(var id:Int = 0,
                         var token:String = "",
                         var email:String = "",
                         var memberSince:Int = 0,
                         var password:String?=null)
