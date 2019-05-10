package com.emi.kotlinmvvmretrofit


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("top-headlines?country=us&apiKey=5b7d45a4569b49c4aef6b5bbea12fc23")
    fun getHome(): Call<NewsResponse>
}