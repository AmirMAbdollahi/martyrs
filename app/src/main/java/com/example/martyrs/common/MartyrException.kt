package com.example.martyrs.common

import androidx.annotation.StringRes

class MartyrException(
    val serverMassage: String? = null, @StringRes val userMessage: Int = 0
) : Throwable() {

}