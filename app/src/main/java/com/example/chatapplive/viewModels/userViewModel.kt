package com.example.chatapplive.viewModels

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapplive.modals.ErrorResponse
import com.example.chatapplive.modals.User
import com.example.chatapplive.repositories.UserRepository
import kotlinx.coroutines.launch

class userViewModel(private val userRepository: UserRepository) : ViewModel() {

    val user: LiveData<User> = userRepository.user
    val errorMessage: LiveData<ErrorResponse> = userRepository.errorMessage

    fun loginUser(username: String, password: String) {
        Log.d("Hello", "in view model $username and $password")
        viewModelScope.launch {
            userRepository.loginUser(username, password)
        }
    }

    fun verifyOtp(otp: String, email: String) {
        Log.d("Hello", "in view model $otp")
        viewModelScope.launch {
            userRepository.verifyOtp(otp, email)
        }
    }

    fun getCurrentUser(){
        viewModelScope.launch {
            val res = userRepository.getCurrentUser()
            Log.d("Hello", "In View Modal With Current user $res")
        }
    }

}