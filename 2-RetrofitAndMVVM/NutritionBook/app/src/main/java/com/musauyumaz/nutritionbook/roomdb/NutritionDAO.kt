package com.musauyumaz.nutritionbook.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.musauyumaz.nutritionbook.model.Nutrition

@Dao
interface NutritionDAO {

    @Insert
    suspend fun insertAll(vararg nutrition: Nutrition) : List<Long>

    @Query("SELECT * FROM nutrition where id = :nutritionId")
    suspend fun getNutrition(nutritionId: Int) : Nutrition

    @Query("SELECT * FROM nutrition")
    suspend fun getAll() : List<Nutrition>

    @Query("DELETE FROM nutrition")
    suspend fun deleteAll()

}