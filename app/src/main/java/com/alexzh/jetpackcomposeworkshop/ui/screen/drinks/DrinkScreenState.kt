package com.alexzh.jetpackcomposeworkshop.ui.screen.drinks

import com.alexzh.jetpackcomposeworkshop.data.OrderDrink

data class DrinkScreenState(
    val drinks: List<OrderDrink>,
    val addedDrinksCount: Int
)
