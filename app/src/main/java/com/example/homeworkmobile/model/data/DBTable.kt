package com.example.homeworkmobile.model.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "firstName")
    val firstName: String? = null,
    @ColumnInfo(name = "password")
    val password: String? = null,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "isClient")
    val isClient: Boolean? = null,
    @ColumnInfo(name = "status")
    val status: Boolean? = null
): Serializable

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "accountNumber")
    var accountNumber: String? = null,
    @ColumnInfo(name = "accountBalance")
    var accountBalance: Double? = null,
    @ColumnInfo(name = "dateCreated")
    var dateCreated: String? = null,
    @ColumnInfo(name = "userId")
    var userId: Int? = null,
    @ColumnInfo(name = "status")
    var status: Boolean? = null
): Serializable

