package com.musauyumaz.nutritionbook.service

import com.musauyumaz.nutritionbook.model.Nutrition
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutritionAPIService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NutritionAPI::class.java)

    suspend fun getData() : List<Nutrition>{
        return retrofit.getNutrition()
    }
}