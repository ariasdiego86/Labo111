package com.example.laboratorio11

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.laboratorio11.network.retrofit.RetrofitInstance
import com.example.laboratorio11.network.service.AuthService
import com.example.laboratorio11.repository.CredentialsRepository

class RetrofitApplication : Application() {

    // TODO: Create a variable to store the token in the SharedPreferences

    // TODO: Get the API service from the Retrofit instance and set the token

    // TODO: Create a function to get the token

    // TODO: Initialize the repository

    // TODO: Create a function to save the token in the SharedPreferences

    companion object {
        const val USER_TOKEN = "user_token"
    }
}