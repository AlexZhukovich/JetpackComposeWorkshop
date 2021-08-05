package com.alexzh.jetpackcomposeworkshop.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

object RuntimeOrderDrinkRepository : OrderDrinkRepository {
    private const val MIN_DRINK_COUNT = 0
    private const val MAX_DRINK_COUNT = 99
    private const val DEFAULT_DRINK_COUNT = 0
    private const val INVALID_INDEX = -1

    private val orderDrinks = mutableListOf<OrderDrink>()

    override suspend fun getAllOrderDrinks(): Flow<List<OrderDrink>> {
        if (orderDrinks.isEmpty()) {
            orderDrinks.addAll(DummyData.getAllDrinks())
        }
        return flowOf(orderDrinks)
    }

    override suspend fun getAddedOrderDrinks(): Flow<List<OrderDrink>> {
        return getAllOrderDrinks()
            .map { orderDrinks ->
                orderDrinks.filter { orderCoffeeDrink ->
                    orderCoffeeDrink.count != DEFAULT_DRINK_COUNT
                }
            }
    }

    override suspend fun add(coffeeDrinkId: Long): Flow<Boolean> {
        val index = orderDrinks.indexOfFirst { it.id == coffeeDrinkId }
        val result = if (index != INVALID_INDEX) {
            val orderDrink = orderDrinks[index]
            val newCountValue = if (orderDrink.count == MAX_DRINK_COUNT)
                MAX_DRINK_COUNT
            else
                orderDrink.count + 1

            orderDrinks[index] = orderDrink.copy(count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun remove(coffeeDrinkId: Long): Flow<Boolean> {
        val index = orderDrinks.indexOfFirst { it.id == coffeeDrinkId }
        val result = if (index != INVALID_INDEX) {
            val orderCoffeeDrink = orderDrinks[index]
            val newCountValue = if (orderCoffeeDrink.count == MIN_DRINK_COUNT)
                MIN_DRINK_COUNT
            else
                orderCoffeeDrink.count - 1

            orderDrinks[index] = orderCoffeeDrink.copy(count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun clear() {
        orderDrinks.clear()
    }
}