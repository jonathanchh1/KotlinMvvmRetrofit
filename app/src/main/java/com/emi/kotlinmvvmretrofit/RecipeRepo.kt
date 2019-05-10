package com.emi.kotlinmvvmretrofit

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipeRepo(private val recipeDao: RecipeDao, private val recipeView : RecipeViewModel) {


     val allRecipes : LiveData<List<Recipe>> = recipeDao.getALLRecipes()

    fun loadRecipes() : LiveData<List<Recipe>> {

        val api = client.create(ApiService::class.java)
        val call = api.getHome()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                Log.d("its message", response.body()?.result.toString())
                val recipes = response.body()

                recipes?.result?.forEach {
                    res ->
                    recipeView.insert(res)
                 }

               /*
                recipes?.let {
                    res ->
                    recipeView.insert(res)
                }

                */
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("failture", t.toString())
            }
        })

        return allRecipes
    }



    @WorkerThread
    suspend fun insertRecipe(recipe: Recipe){
        recipeDao.insert(recipe)
    }


    companion object{
        val baseUrl = "https://newsapi.org/v2/"
        var retrofit : Retrofit?=null

        val client : Retrofit
        get(){

            if(retrofit == null){

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }
    }

}