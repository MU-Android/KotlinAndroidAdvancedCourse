package com.musauyumaz.nutritionbook.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.musauyumaz.nutritionbook.model.Nutrition

@Database(entities = [Nutrition::class], version = 1)
abstract class NutritionDatabase : RoomDatabase(){
    abstract fun nutritionDao() : NutritionDAO
}