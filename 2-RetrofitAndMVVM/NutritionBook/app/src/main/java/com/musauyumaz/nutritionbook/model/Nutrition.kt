package com.musauyumaz.nutritionbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Nutrition(
    @ColumnInfo("isim")
    @SerializedName("isim")
    val name: String?,
    @ColumnInfo("kalori")
    @SerializedName("kalori")
    val calorie: String?,
    @ColumnInfo("karbonhidrat")
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,
    @ColumnInfo("protein")
    @SerializedName("protein")
    val protein: String?,
    @ColumnInfo("yag")
    @SerializedName("yag")
    val fat: String?,
    @ColumnInfo("gorsel")
    @SerializedName("gorsel")
    val pictureUrl: String?
){
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}