package com.example.homeworkmobile.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworkmobile.model.data.Account
@Dao
interface AccountsDao {
    @Query("SELECT * FROM accounts WHERE userId = (:userId) AND status = 1")
    fun getAccount(userId: Int): LiveData<List<Account?>>

    @Query("SELECT COUNT(*) from accounts WHERE accountNumber = (:accountNumber)")
    fun accountExist(accountNumber: String) : Boolean

    @Query("UPDATE accounts SET accountBalance = :value WHERE Id = :Id")
    fun replenishAccount(value: Double, Id: Int)

    @Query("UPDATE accounts SET status = 0 WHERE Id = (:Id)")
    fun closeAccount(Id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createAccount(account: Account)

    @Delete
    fun delete(account: Account): Int
}