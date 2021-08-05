package com.alexzh.jetpackcomposeworkshop.data

data class OrderDrink(
    val id: Long,
    val emoji: String,
    val title: String,
    val description: String,
    val count: Int,
    val price: Double
)
