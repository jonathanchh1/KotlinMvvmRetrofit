package com.emi.kotlinmvvmretrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeViewModel(application : Application) : AndroidViewModel(application) {

    var recipeRepo : RecipeRepo
    var recipesAll : LiveData<List<Recipe>>


    init {
        val recipesDao = RecipeRoomDatabase.getDatabase(application.applicationContext, viewModelScope).recipeDao()
        recipeRepo = RecipeRepo(recipesDao, this)
        recipesAll = recipeRepo.loadRecipes()
    }


    fun insert(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO){
        recipeRepo.insertRecipe(recipe)
    }

    companion object{
        const val Apikey = "5b7d45a4569b49c4aef6b5bbea12fc23"
    }
}