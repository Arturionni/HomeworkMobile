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
    val email: String? = null
): Serializable

