package com.alexzh.jetpackcomposeworkshop.data

import kotlinx.coroutines.flow.Flow

interface OrderDrinkRepository {

    suspend fun getAllOrderDrinks(): Flow<List<OrderDrink>>

    suspend fun getAddedOrderDrinks(): Flow<List<OrderDrink>>

    suspend fun add(coffeeDrinkId: Long): Flow<Boolean>

    suspend fun remove(coffeeDrinkId: Long): Flow<Boolean>

    suspend fun clear()
}