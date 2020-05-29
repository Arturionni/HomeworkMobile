package com.example.homeworkmobile.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.model.data.User

@Database(entities = [User::class, Account::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun accountsDao(): AccountsDao
}