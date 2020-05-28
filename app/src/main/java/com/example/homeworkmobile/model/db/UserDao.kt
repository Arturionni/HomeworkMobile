package com.example.homeworkmobile.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworkmobile.model.data.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email IN (:email)")
    fun getUser(email: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createUser(user: User)

    @Delete
    fun delete(user: User): Int
}