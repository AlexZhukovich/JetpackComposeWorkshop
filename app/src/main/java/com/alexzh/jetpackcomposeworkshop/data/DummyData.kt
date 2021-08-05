package com.alexzh.jetpackcomposeworkshop.data

object DummyData {

    fun getAllDrinks(): List<OrderDrink> {
        return listOf(
            OrderDrink(
                id = 1L,
                emoji = "☕️",
                title = "Drink 1",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 4.50
            ),
            OrderDrink(
                id = 2L,
                emoji = "🍵",
                title = "Drink 2",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 4.0
            ),
            OrderDrink(
                id = 3L,
                emoji = "🥛",
                title = "Drink 3",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 2.50
            ),
            OrderDrink(
                id = 4L,
                emoji = "🍺",
                title = "Drink 4",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 3.5
            ),
            OrderDrink(
                id = 5L,
                emoji = "🥂",
                title = "Drink 5",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 7.2
            ),
            OrderDrink(
                id = 6L,
                emoji = "🍷",
                title = "Drink 6",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 6.0
            ),
            OrderDrink(
                id = 7L,
                emoji = "🥃",
                title = "Drink 7",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 8.0
            ),
            OrderDrink(
                id = 8L,
                emoji = "🍸",
                title = "Drink 8",
                description = "Long boring description. Long boring description. Long boring description",
                count = 0,
                price = 5.8
            )
        )
    }
}