package com.musauyumaz.nutritionbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.musauyumaz.nutritionbook.databinding.FragmentNutritionListBinding
import com.musauyumaz.nutritionbook.model.Nutrition
import com.musauyumaz.nutritionbook.service.NutritionAPI
import com.musauyumaz.nutritionbook.viewmodel.NutritionListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutritionListFragment : Fragment() {
    private var _binding: FragmentNutritionListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NutritionListViewModel::class.java]
        viewModel.refreshData()

        binding.recyclerViewNutrition.layoutManager = LinearLayoutManager(requireContext())
        //binding.recyclerViewNutrition.adapter =

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerViewNutrition.visibility = View.GONE
            binding.txtErrorMessage.visibility = View.GONE
            binding.progressBarNutrition.visibility = View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeLiveData(){
        viewModel.nutritions.observe(viewLifecycleOwner){

            binding.recyclerViewNutrition.visibility = View.VISIBLE
        }

        viewModel.nutritionError.observe(viewLifecycleOwner){
            binding.txtErrorMessage.visibility = if (it) View.VISIBLE else View.GONE
            binding.recyclerViewNutrition.visibility = View.GONE
        }

        viewModel.nutritionLoading.observe(viewLifecycleOwner){
            if(it){
                binding.txtErrorMessage.visibility = View.GONE
                binding.recyclerViewNutrition.visibility = View.GONE
                binding.progressBarNutrition.visibility = View.VISIBLE
            }else{
                binding.progressBarNutrition.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}