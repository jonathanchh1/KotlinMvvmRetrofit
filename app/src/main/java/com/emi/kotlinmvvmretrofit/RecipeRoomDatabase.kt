package com.emi.kotlinmvvmretrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Recipe::class], version = 6)
abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDao() : RecipeDao

    companion object{
        @Volatile
        private var INSTANCE : RecipeRoomDatabase?=null

        fun getDatabase(context : Context, scope : CoroutineScope) : RecipeRoomDatabase{

            return INSTANCE?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeRoomDatabase::class.java,
                    "recipe_database"
                )

                    .fallbackToDestructiveMigration()
                    .addCallback(RecipeRoomDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

       private class RecipeRoomDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){

           override fun onOpen(db: SupportSQLiteDatabase) {
               super.onOpen(db)

               INSTANCE?.let {
                   database ->
                   scope.launch(Dispatchers.IO){
                       populationDatabase(database.recipeDao())
                   }
               }
           }
       }

        suspend fun populationDatabase(recipeDao: RecipeDao){
            recipeDao.deleteAll()
        }
    }
}