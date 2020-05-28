package com.example.homeworkmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory @Inject constructor(
    private val userViewModel: UserViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(userViewModel::class.java)) {
            return userViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}