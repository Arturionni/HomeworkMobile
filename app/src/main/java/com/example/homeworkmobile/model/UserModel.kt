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
    suspend fun getUserFromDb(email: String): LiveData<User?> {
        return withContext(Dispatchers.IO) { userDao.getUser(email) }
    }
    suspend fun addUserToDB(user: User) {
        withContext(Dispatchers.IO) { userDao.createUser(user) }
    }
    suspend fun userExist(email: String): Boolean  {
        return withContext(Dispatchers.IO) { userDao.userExist(email) }
    }
}