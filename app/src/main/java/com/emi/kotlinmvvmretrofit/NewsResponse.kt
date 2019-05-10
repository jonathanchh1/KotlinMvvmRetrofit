package com.emi.kotlinmvvmretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @Expose
    @SerializedName("status")
    var status: String = "",
    @Expose
    @SerializedName("source")
    var source : String = "",
    @Expose
    @SerializedName("totalResults")
    var totalResults : Int?,
    @Expose
    @SerializedName("articles")
    var result : List<Recipe>)