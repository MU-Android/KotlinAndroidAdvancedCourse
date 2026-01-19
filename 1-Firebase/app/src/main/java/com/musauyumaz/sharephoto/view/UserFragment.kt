package com.musauyumaz.sharephoto.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.musauyumaz.sharephoto.UserFragmentDirections
import com.musauyumaz.sharephoto.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private var email: String = ""
    private var password: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener(::register)
        binding.btnLogin.setOnClickListener(::login)

        if(auth.currentUser != null){
            val action = UserFragmentDirections.Companion.actionUserFragmentToFeedFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun register(view: View){
        email = binding.edtEmail.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()

        if (email.isNotBlank() && password.isNotBlank()){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val action = UserFragmentDirections.Companion.actionUserFragmentToFeedFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }.addOnFailureListener { exception ->
                showErrorDialog(exception.localizedMessage ?: "Bir hata oluştu")
            }
        } else {
            showErrorDialog("Lütfen email ve şifre giriniz!")
        }
    }

    private fun login(view: View){
        email = binding.edtEmail.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()

        if (email.isNotBlank() && password.isNotBlank()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val action = UserFragmentDirections.Companion.actionUserFragmentToFeedFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }.addOnFailureListener { exception ->
                showErrorDialog(exception.localizedMessage ?: "Bir hata oluştu")
            }
        } else {
            showErrorDialog("Lütfen email ve şifre giriniz!")
        }
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hata")
            .setMessage(message)
            .setPositiveButton("Tamam") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}