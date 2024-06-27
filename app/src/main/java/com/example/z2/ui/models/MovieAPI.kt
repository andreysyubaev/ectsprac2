package com.example.z2.ui.models

import retrofit2.Call
import retrofit2.http.GET

interface MovieAPI {
    @GET("https://dummyapi.online/api/movies")
    fun getMovie(): Call<List<Movie>>
}