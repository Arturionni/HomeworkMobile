package com.example.homeworkmobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homeworkmobile.model.AccountModel
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.model.data.User
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val accountModel: AccountModel) : ViewModel() {

    suspend fun getAccountsFromDb(userId: Int): LiveData<List<Account?>> {
        return accountModel.getAccountsFromDb(userId)
    }
    suspend fun addAccountToDB(userId: Int) {
        return accountModel.addAccountToDB(userId)
    }
    suspend fun closeAccount(Id: Int) {
        return accountModel.closeAccount(Id)
    }
    suspend fun replenishAccount(value: Double, account: Account) {
        return accountModel.replenishAccount(value, account)
    }
    suspend fun getAccount(accountNumber: String): Account? {
        return accountModel.getAccount(accountNumber)
    }
    suspend fun transferAccount(value: Double, account: Account) {
        return accountModel.transferAccount(value, account)
    }
    suspend fun accountExist(accountNumber: String): Boolean {
        return accountModel.accountExist(accountNumber)
    }
}