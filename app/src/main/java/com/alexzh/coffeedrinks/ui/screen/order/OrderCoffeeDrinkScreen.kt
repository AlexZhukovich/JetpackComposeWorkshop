package com.alexzh.coffeedrinks.ui.screen.order

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.ui.component.AppDivider
import com.alexzh.coffeedrinks.ui.component.Counter
import com.alexzh.coffeedrinks.ui.screen.order.model.OrderCoffeeDrinkState
import java.math.BigDecimal

@Composable
fun ShowLoadingOrderCoffeeDrinksScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .align(Alignment.Center)
                .size(36.dp)
        )
    }
}

@Composable
fun ShowSuccessOrderCoffeeDrinksScreen(
    orderCoffeeDrinkState: OrderCoffeeDrinkState,
    viewModel: OrderCoffeeDrinkViewModel,
    onBack: () -> Unit
) {
    Column {
        AppBarWithOrderSummary(
            totalPrice = orderCoffeeDrinkState.totalPrice,
            onBackClick = onBack
        )
        Surface {
            LazyColumn {
                items(items = orderCoffeeDrinkState.coffeeDrinks) { coffeeDrink ->
                    Column {
                        OrderCoffeeDrinkItem(
                            orderCoffeeDrink = coffeeDrink,
                            onAdded = { coffeeDrinkId -> viewModel.addDrink(coffeeDrinkId) },
                            onRemoved = { coffeeDrinkId -> viewModel.removeDrink(coffeeDrinkId) }
                        )
                        AppDivider(PaddingValues(start = 72.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ShowErrorOrderCoffeeDrinksScreen() {
    // TODO: implement UI
}

@Composable
private fun AppBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Order coffee drinks",
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onPrimary
                )
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = BitmapPainter(ImageBitmap.imageResource(id = R.drawable.ic_arrow_back_white)),
                    contentDescription = stringResource(R.string.action_back),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}

@Composable
fun OrderCoffeeDrinkItem(
    orderCoffeeDrink: com.alexzh.coffeedrinks.data.order.OrderCoffeeDrink,
    onAdded: (Long) -> Unit,
    onRemoved: (Long) -> Unit
) {
    Box(modifier = Modifier.padding(top = 16.dp, end = 16.dp)) {
        Row {
            Logo(orderCoffeeDrink.imageUrl)
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Column {
                    Text(
                        text = orderCoffeeDrink.name,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = orderCoffeeDrink.ingredients,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Box(
                modifier = Modifier.width(120.dp)
                    .padding(start = 8.dp)
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        text = "??? ${orderCoffeeDrink.price}",
                        style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Right)
                    )
                    Counter(
                        orderCoffeeDrink,
                        onAdded,
                        onRemoved
                    )
                }
            }
        }
    }
}

@Composable
private fun Logo(
    @DrawableRes logoId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(72.dp)
            .padding(16.dp),
        shape = CircleShape,
        color = Color(0xFFFAFAFA)
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = BitmapPainter(ImageBitmap.imageResource(id = logoId)),
            contentDescription = null
        )
    }
}

@Composable
private fun AppBarWithOrderSummary(
    totalPrice: BigDecimal,
    onBackClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        Column {
            AppBar {
                onBackClick()
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Total cost",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onPrimary
                    )
                )
                Text(
                    text = "??? $totalPrice",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onPrimary
                    )
                )
            }
        }
    }
}
