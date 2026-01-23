package com.musauyumaz.nutritionbook.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.musauyumaz.nutritionbook.model.Nutrition

@Database(entities = [Nutrition::class], version = 1)
abstract class NutritionDatabase : RoomDatabase(){
    abstract fun nutritionDao() : NutritionDAO

    companion object{

        @Volatile private var instance : NutritionDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NutritionDatabase::class.java,
            "NutritionDatabase"
        ).build()
    }
}