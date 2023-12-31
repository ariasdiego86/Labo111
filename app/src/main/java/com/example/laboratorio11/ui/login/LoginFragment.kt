package com.example.laboratorio11.ui.login

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
import com.example.laboratorio11.databinding.FragmentLoginBinding
import com.example.laboratorio11.ui.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    // TODO: Declare the LoginViewModel using the activityViewModels property delegate

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    val app by lazy {
        requireActivity().application as RetrofitApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Uncomment

        setViewModel()
        observeStatus()


        binding.registerBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    // TODO: Create a function to set the view model

    private fun setViewModel() {
        binding.viewmodel = loginViewModel
    }


    // TODO: Create a function to observe the status LiveData

    private fun observeStatus() {
        loginViewModel.status.observe(viewLifecycleOwner) { status ->
            handleUiStatus(status)
        }
    }


    // TODO: Create a function to handle the UI status

    private fun handleUiStatus(status: LoginUiStatus) {
        when(status) {
            is LoginUiStatus.Error -> {
                Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT).show()
            }
            is LoginUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            }
            is LoginUiStatus.Success -> {
                loginViewModel.clearStatus()
                loginViewModel.clearData()
                app.saveAuthToken(status.token)
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }

            else -> {}
        }
    }


}