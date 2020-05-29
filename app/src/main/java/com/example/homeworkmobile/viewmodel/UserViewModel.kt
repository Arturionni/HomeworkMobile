package com.example.homeworkmobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homeworkmobile.model.UserModel
import com.example.homeworkmobile.model.data.User
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userModel: UserModel) : ViewModel() {

    suspend fun getUserFromDb(email: String): LiveData<User?> {
        return userModel.getUserFromDb(email)
    }
    suspend fun addUserToDB(user: User) {
        return userModel.addUserToDB(user)
    }
    suspend fun userExist(email: String): Boolean {
        return userModel.userExist(email)
    }
}