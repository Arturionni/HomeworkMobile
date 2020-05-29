package com.example.homeworkmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AccountViewModelFactory @Inject constructor(
    private val accountViewModel: AccountViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(accountViewModel::class.java)) {
            return accountViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}