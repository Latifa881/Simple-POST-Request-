package com.example.simplepostrequest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/custom-people/")
    fun getNames(): Call<ArrayList<Names.Name>>

    @Headers("Content-Type: application/json")
    @POST("/custom-people/")
   // fun addNames(@Body name: String): Call<List<Names.Name>>
    fun addNames(@Body name: Names.Name): Call<List<Names.Name>>
}