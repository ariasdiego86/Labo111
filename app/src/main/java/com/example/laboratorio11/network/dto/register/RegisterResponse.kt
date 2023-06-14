package com.example.laboratorio11.network.dto.register

import com.google.gson.annotations.SerializedName

// TODO: Create RegisterResponse data class
data class RegisterResponse(
    // TODO: Specify the name of the fields in the JSON response
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

