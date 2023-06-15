package com.example.laboratorio11.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.laboratorio11.R
import com.example.laboratorio11.RetrofitApplication
import com.example.laboratorio11.databinding.FragmentRegisterBinding
import com.example.laboratorio11.ui.register.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : Fragment() {

    // TODO: Declare the RegisterViewModel using the activityViewModels property delegate
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    val app by lazy {
        requireActivity().application as RetrofitApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // TODO: Create a function to set the view model
    private fun setRegister(){
        binding.viewmodel = registerViewModel
    }

    // TODO: Create a function to observe the status LiveData
    private fun observeStatus(){
        registerViewModel.status.observe(viewLifecycleOwner){status ->
            handleUiStatus(status)
        }
    }

    private fun handleUiStatus(status: RegisterUiStatus){
        when(status){
            is RegisterUiStatus.Error -> {
                Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT).show()
            }

            is RegisterUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            }

            is RegisterUiStatus.Success -> {
                registerViewModel.clearStatus()
                registerViewModel.clearData()
                app.getToken()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

            }
            else -> {}
        }
    }

    /* TODO: Create a function to handle the status of the UI
        - If the status is an error, show a Toast with the message "An error has occurred"
        - If the status is an error with a message, show a Toast with the message
        - If the status is a success, clear the status and the data from the view model and navigate back
     */
}