package com.example.eater

data class LoginResponse(var id:Int?,
                         var token:String?,
                         var email:String?,
                         var memberSince:Int?,
                         var password:String?)
