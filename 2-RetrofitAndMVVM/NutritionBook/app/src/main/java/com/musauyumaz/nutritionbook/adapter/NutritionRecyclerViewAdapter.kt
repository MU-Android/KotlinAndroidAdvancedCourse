package com.musauyumaz.nutritionbook.adapter

import android.R.attr.text
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.musauyumaz.nutritionbook.databinding.NutritionRecyclerRowBinding
import com.musauyumaz.nutritionbook.model.Nutrition
import com.musauyumaz.nutritionbook.view.NutritionListFragmentDirections

class NutritionRecyclerViewAdapter(val nutritionList: ArrayList<Nutrition>): RecyclerView.Adapter<NutritionRecyclerViewAdapter.NutritionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NutritionViewHolder {
        val binding = NutritionRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NutritionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder,position: Int) {
        holder.binding.txtName.text = nutritionList[position].name
        holder.binding.txtCalorie.text = nutritionList[position].calorie

        holder.itemView.setOnClickListener {
            val action = NutritionListFragmentDirections.actionNutritionListFragmentToNutritionDetailFragment(nutritionList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateNutritionList(newNutritionList: List<Nutrition>) {
        nutritionList.clear()
        nutritionList.addAll(newNutritionList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return nutritionList.size
    }

    class NutritionViewHolder(val binding: NutritionRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }
}