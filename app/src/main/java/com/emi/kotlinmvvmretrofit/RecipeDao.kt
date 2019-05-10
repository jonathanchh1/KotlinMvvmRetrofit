package com.emi.kotlinmvvmretrofit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RecipeDao{

    @Query("SELECT * FROM recipe_table ORDER BY title ASC")
    fun getALLRecipes() : LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Query("DELETE from recipe_table")
    fun deleteAll()

}