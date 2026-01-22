package com.musauyumaz.nutritionbook.service

import com.musauyumaz.nutritionbook.model.Nutrition
import retrofit2.http.GET

interface NutritionAPI {
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json")
    suspend fun getNutrition(): List<Nutrition>
}


