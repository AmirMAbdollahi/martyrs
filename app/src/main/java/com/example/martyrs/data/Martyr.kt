package com.example.martyrs.data

data class Martyr(
    val result: Result,
    val success: Boolean
)

const val NO_SORT = ""
const val MIN_AGE_SORT = "MinAge"
const val MAX_AGE_SORT = "MaxAge"
const val FIRST_NAME_SORT = "FirstName"
const val LAST_NAME_SORT = "LastName"