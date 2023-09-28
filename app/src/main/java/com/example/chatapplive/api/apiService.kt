package com.example.chatapplive.api

import com.example.chatapplive.modals.LoginPayload
import com.example.chatapplive.modals.OtpPayload
import com.example.chatapplive.modals.SuccessResponse
import com.example.chatapplive.modals.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface apiService {

    @POST("user/login")
    suspend fun loginUser(@Body payload: LoginPayload): Response<User>

    @POST("user/verifyotp")
    suspend fun verifyOtp(@Body payload: OtpPayload): Response<User>

    @GET("user/get")
    suspend fun getCurrentUser() : User

}