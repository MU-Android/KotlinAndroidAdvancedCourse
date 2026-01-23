package com.musauyumaz.nutritionbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musauyumaz.nutritionbook.model.Nutrition
import com.musauyumaz.nutritionbook.roomdb.NutritionDatabase
import kotlinx.coroutines.launch

class NutritionDetailViewModel(application: Application) : AndroidViewModel(application){
    val nutritionLiveData = MutableLiveData<Nutrition>()

    fun roomGetData(id: Int){
        viewModelScope.launch {
            val dao = NutritionDatabase(getApplication()).nutritionDao()
            val nutrition = dao.getNutrition(id)
            nutritionLiveData.value = nutrition
        }
    }
}