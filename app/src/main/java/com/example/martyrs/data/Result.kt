package com.example.martyrs.data

data class Result(
    val `data`: List<Data>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int
)