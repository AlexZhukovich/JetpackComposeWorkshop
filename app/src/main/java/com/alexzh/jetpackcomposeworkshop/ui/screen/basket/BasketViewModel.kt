package com.alexzh.jetpackcomposeworkshop.ui.screen.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.jetpackcomposeworkshop.data.OrderDrinkRepository
import com.alexzh.jetpackcomposeworkshop.data.RuntimeOrderDrinkRepository
import com.alexzh.jetpackcomposeworkshop.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BasketViewModel(
    private val repository: OrderDrinkRepository = RuntimeOrderDrinkRepository
) : ViewModel() {

    private val _uiState: MutableLiveData<UiState<BasketScreenState>> = MutableLiveData()
    val uiState: LiveData<UiState<BasketScreenState>>
        get() = _uiState

    fun loadDrinks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderDrinks()
                .collect { orderDrinks ->
                    val totalPrice = orderDrinks.map { it.count * it.price }
                        .sum()

                    _uiState.value = UiState.Success(BasketScreenState(orderDrinks, totalPrice))
                }
        }
    }

    fun addDrink(drinkId: Long) {
        viewModelScope.launch {
            repository.add(drinkId)
                .collect { isAdded ->
                    if (isAdded) {
                        loadDrinks()
                    }
                }
        }
    }

    fun removeDrink(drinkId: Long) {
        viewModelScope.launch {
            repository.remove(drinkId)
                .collect { isRemoved ->
                    if (isRemoved) {
                        loadDrinks()
                    }
                }
        }
    }
}