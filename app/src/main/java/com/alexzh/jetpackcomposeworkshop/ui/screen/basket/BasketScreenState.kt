package com.alexzh.jetpackcomposeworkshop.ui.screen.basket

import com.alexzh.jetpackcomposeworkshop.data.OrderDrink

data class BasketScreenState(
    val drinks: List<OrderDrink>,
    val totalPrice: Double
)
