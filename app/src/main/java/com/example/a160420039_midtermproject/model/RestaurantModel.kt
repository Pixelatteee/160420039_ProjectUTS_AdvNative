package com.example.a160420039_midtermproject.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id:String?,
    @SerializedName("restaurant_name")
    val restaurantName:String?,
    @SerializedName("restaurant_rating")
    val rating:String?,
    val description:String?,
    val photoUrl:String?
)