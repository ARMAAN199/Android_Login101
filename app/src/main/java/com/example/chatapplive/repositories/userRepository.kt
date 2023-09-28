package com.example.chatapplive.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatapplive.api.apiService
import com.example.chatapplive.modals.ErrorResponse
import com.example.chatapplive.modals.LoginPayload
import com.example.chatapplive.modals.OtpPayload
import com.example.chatapplive.modals.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: apiService) {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _errorMessage = MutableLiveData<ErrorResponse>()
    val errorMessage: LiveData<ErrorResponse> get() = _errorMessage

    suspend fun loginUser(username: String, password: String) {
        val payload = LoginPayload(username, password)

        try {
            val result = withContext(Dispatchers.IO) {
                Log.d("Hello", "in repository $payload")
                api.loginUser(payload)
            }

            if (result.isSuccessful) {
                val user = result.body()!!
                Log.d("Hello", "user logged in ${result.body()}")
                _user.postValue(user)
                val blankError = ErrorResponse(message = "", code = -1)
                _errorMessage.postValue(blankError)
            } else {
                val errorString = result.errorBody()?.string()
                val gson = Gson()
                val errorResponse: ErrorResponse = gson.fromJson(errorString, ErrorResponse::class.java)
                if (errorResponse.message != null) {
                    _errorMessage.postValue(errorResponse)
                }
            }
        } catch (e: Exception) {
            Log.e("Hello", "Error in loginUser: ", e)
        }
    }

    suspend fun verifyOtp(otp : String, email: String) {
        val payload = OtpPayload(otp, email)
        try {
            val result = withContext(Dispatchers.IO) {
                Log.d("Hello", "in repository sending otp $payload")
                api.verifyOtp(payload)
            }

            if (result.isSuccessful) {
                val user = result.body()!!
                Log.d("Hello", "user has logged in ${result.body()}")
                    _user.postValue(user)
                    val blankError = ErrorResponse(message = "", code = -1)
                    _errorMessage.postValue(blankError)
            } else {
                val errorString = result.errorBody()?.string()
                val gson = Gson()
                val errorResponse: ErrorResponse = gson.fromJson(errorString, ErrorResponse::class.java)
                if (errorResponse.message != null) {
                    _errorMessage.postValue(errorResponse)
                }
            }
        } catch (e: Exception) {
            Log.e("Hello", "Error in verify OTP: ", e)
        }
    }

    suspend fun getCurrentUser(){
        try{
            val resultUser = withContext(Dispatchers.IO) {
                api.getCurrentUser()
            }
//            Log.d("Hello", "current user $resultUser")
        }
        catch (e: Exception) {
            Log.e("Hello", "Error in currentUser: ", e)
        }
    }
}