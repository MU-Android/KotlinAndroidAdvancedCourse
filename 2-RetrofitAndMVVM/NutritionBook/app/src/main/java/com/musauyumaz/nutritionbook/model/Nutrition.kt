package com.musauyumaz.nutritionbook.model

import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("isim")
    val name: String?,
    @SerializedName("kalori")
    val calorie: String?,
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,
    @SerializedName("protein")
    val protein: String?,
    @SerializedName("yag")
    val fat: String?,
    @SerializedName("gorsel")
    val pictureUrl: String?
)