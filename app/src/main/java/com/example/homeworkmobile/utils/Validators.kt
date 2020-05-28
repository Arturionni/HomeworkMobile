package com.example.homeworkmobile.utils

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

fun emailValidator(target: CharSequence?): Boolean {
    return if (target == null) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}

fun nameValidator(_Name: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val name = "^[a-zA-Zа-яА-Я\\\\s]+"
    pattern = Pattern.compile(name)
    matcher = pattern.matcher(_Name)
    return matcher.matches()
}

fun passwordValidator(Password: String): Boolean {
    return Password.length >= 4
}
