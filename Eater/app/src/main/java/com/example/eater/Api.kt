package com.example.eater

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
//    @FormUrlEncoded
    @POST("/eaterapp/login")
    suspend fun userLogin(@Body requestBody: RequestBody) : Response<ResponseBody>

    @POST("/eaterapp/register")
    suspend fun userRegister(@Body requestBody: RequestBody) : Response<ResponseBody>

}