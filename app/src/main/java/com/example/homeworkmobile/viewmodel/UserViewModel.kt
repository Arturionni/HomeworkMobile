package com.example.homeworkmobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homeworkmobile.model.UserModel
import com.example.homeworkmobile.model.data.User
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userModel: UserModel) : ViewModel() {

    suspend fun getUserFromDb(): LiveData<List<User>> {
        return userModel.getUsersFromDb()
    }

    suspend fun addUserToDB(user: User) {
        return userModel.addUserToDB(user)
    }
}