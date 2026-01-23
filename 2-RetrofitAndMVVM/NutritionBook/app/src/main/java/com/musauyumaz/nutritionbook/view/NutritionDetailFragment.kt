package com.musauyumaz.nutritionbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.musauyumaz.nutritionbook.databinding.FragmentNutritionDetailBinding
import com.musauyumaz.nutritionbook.util.createPlaceHolder
import com.musauyumaz.nutritionbook.util.downloadPicture
import com.musauyumaz.nutritionbook.viewmodel.NutritionDetailViewModel

class NutritionDetailFragment : Fragment() {
    private var _binding: FragmentNutritionDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NutritionDetailViewModel::class.java]

        arguments?.let {
            val nutritionId = NutritionDetailFragmentArgs.fromBundle(it).nutritionId
            viewModel.roomGetData(nutritionId)

            observeLiveData()
        }
    }
    private fun observeLiveData(){
        viewModel.nutritionLiveData.observe(viewLifecycleOwner){
            binding.txtNutritionName.text = it.name
            binding.txtNutritionCalorie.text = it.calorie
            binding.txtNutritionCarbohydrate.text = it.carbohydrate
            binding.txtNutritionFat.text = it.fat
            binding.txtNutritionProtein.text = it.protein
            binding.imageViewNutrition.downloadPicture(it.pictureUrl, createPlaceHolder(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}