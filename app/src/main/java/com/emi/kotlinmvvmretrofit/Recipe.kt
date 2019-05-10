package com.emi.kotlinmvvmretrofit

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "recipe_table")
data class Recipe(@PrimaryKey(autoGenerate = true)
                   var id : Int = 0,
                   @Expose
                   @SerializedName("urlToImage")
                   @ColumnInfo(name = "urlToImage") var thumbnail : String?=null,
                   @Expose
                   @SerializedName("title")
                   @ColumnInfo(name = "title") var title : String?=null,
                   @Expose
                   @SerializedName("url")
                   @ColumnInfo(name = "url") var href : String?=null,
                   @Expose
                   @SerializedName("description")
                   @ColumnInfo(name = "description") var desc : String?=null,
                   @Expose

                   @SerializedName("author")
                   @ColumnInfo(name = "author") var author : String?=null,
                   @Expose
                   @SerializedName( "publishedAt")
                   @ColumnInfo(name =  "publishedAt") var published : String?=null,
                   @SerializedName("content")
                   @ColumnInfo(name = "content") var content : String?=null)

