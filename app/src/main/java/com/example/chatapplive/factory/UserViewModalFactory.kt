package com.example.chatapplive.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapplive.repositories.UserRepository
import com.example.chatapplive.viewModels.userViewModel
import javax.inject.Inject

class UserViewModalFactory @Inject constructor(private val userRepository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return userViewModel(userRepository) as T
    }
}