package com.example.homeworkmobile.views.personalArea.adapters

import com.example.homeworkmobile.model.data.Account


interface AdapterCallback {
    fun onCardClick(account: Account)
}