package com.example.homeworkmobile.model

import androidx.lifecycle.LiveData
import com.example.homeworkmobile.model.data.Account
import com.example.homeworkmobile.model.db.AccountsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountModel @Inject constructor(
    private val accountsDao: AccountsDao)
{
    suspend fun getAccountsFromDb(userId: Int): LiveData<List<Account?>> {
        return withContext(Dispatchers.IO) { accountsDao.getAccount(userId) }
    }
    suspend fun closeAccount(Id: Int) {
        return withContext(Dispatchers.IO) { accountsDao.closeAccount(Id) }
    }
    suspend fun replenishAccount(value: Double, account: Account) {
        val balance = account.accountBalance
        val total = value + balance!!
        return withContext(Dispatchers.IO) { accountsDao.replenishAccount(total, account.id!!) }
    }
    suspend fun addAccountToDB(userId: Int) {
        val account = Account()
        account.id = null
        account.accountBalance = 0.0
        account.accountNumber = ((0..1000000000).random() + 4000000000).toString()
        account.dateCreated = java.util.Calendar.getInstance().time.toString()
        account.userId = userId
        account.status = true
        withContext(Dispatchers.IO) { accountsDao.createAccount(account) }
    }
    suspend fun accountExist(accountNumber: String): Boolean  {
        return withContext(Dispatchers.IO) { accountsDao.accountExist(accountNumber) }
    }
}