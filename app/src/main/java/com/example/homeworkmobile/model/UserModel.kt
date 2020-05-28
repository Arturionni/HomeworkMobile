package com.example.homeworkmobile.model

import androidx.lifecycle.LiveData
import com.example.homeworkmobile.model.data.User
import com.example.homeworkmobile.model.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserModel @Inject constructor(
    private val userDao: UserDao)
  {
    suspend fun getUsersFromDb(): LiveData<List<User>> {
        return withContext(Dispatchers.IO) { userDao.getAll() }
    }
    suspend fun addUserToDB(user: User) {
        withContext(Dispatchers.IO) { userDao.createUser(user) }
    }
}