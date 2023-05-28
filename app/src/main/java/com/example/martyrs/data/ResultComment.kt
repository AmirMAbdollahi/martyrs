package com.example.martyrs.data

data class ResultComment(
    val `data`: List<DataComment>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int
)