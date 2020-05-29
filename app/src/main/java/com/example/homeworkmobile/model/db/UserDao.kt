package com.example.homeworkmobile.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworkmobile.model.data.User
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email = (:email)")
    fun getUser(email: String): LiveData<User?>

    @Query("SELECT COUNT(*) from user WHERE email = (:email) LIMIT 1")
    fun userExist(email: String) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createUser(user: User)

    @Delete
    fun delete(user: User): Int
}