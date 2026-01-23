package com.musauyumaz.nutritionbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musauyumaz.nutritionbook.model.Nutrition
import com.musauyumaz.nutritionbook.roomdb.NutritionDatabase
import com.musauyumaz.nutritionbook.service.NutritionAPIService
import com.musauyumaz.nutritionbook.util.PrivacySharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NutritionListViewModel(application: Application) : AndroidViewModel(application)  {

    val nutritions = MutableLiveData<List<Nutrition>>()
    val nutritionError = MutableLiveData<Boolean>()
    val nutritionLoading = MutableLiveData<Boolean>()
    private val privacySharedPreferences = PrivacySharedPreferences(getApplication())
    private val nutritionAPIService = NutritionAPIService()
    private val updateTime = 10 * 60 * 1000 * 1000 * 1000L
    fun refreshData(){
        val savedTime = privacySharedPreferences.getTime()
        if (savedTime != null && savedTime != 0L && System.nanoTime() - savedTime < updateTime) {
            getDataFromRoom()
        }else{
            getDataOnInternet()
        }

    }

    fun refreshDataFromInternet(){
        getDataOnInternet()
    }
    private fun getDataOnInternet(){
        nutritionLoading.value = true
        viewModelScope.launch {
            val nutritionList = nutritionAPIService.getData()
            withContext(Dispatchers.Main){
                nutritionLoading.value = false
                saveRoom(nutritionList)
                Toast.makeText(getApplication(),"Besinleri internetten aldık", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getDataFromRoom() {
        nutritionLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val nutritionList = NutritionDatabase(getApplication()).nutritionDao().getAll()
            withContext(Dispatchers.Main){
                showNutritions(nutritionList)
                Toast.makeText(getApplication(), "Besinleri Roomdan aldık", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun showNutritions(nutritionList: List<Nutrition>){
        nutritions.value = nutritionList
        nutritionError.value = false
        nutritionLoading.value = false
    }
    private fun saveRoom(nutritionList: List<Nutrition>){
        viewModelScope.launch(Dispatchers.IO) {
            val dao = NutritionDatabase(getApplication()).nutritionDao()
            dao.deleteAll()
            val uuidList = dao.insertAll(*nutritionList.toTypedArray())
            var i = 0
            while (i < nutritionList.size){
                nutritionList[i].id = uuidList[i].toInt()
                i++
            }
            showNutritions(nutritionList)
        }
        privacySharedPreferences.saveTime(System.nanoTime())
    }
}