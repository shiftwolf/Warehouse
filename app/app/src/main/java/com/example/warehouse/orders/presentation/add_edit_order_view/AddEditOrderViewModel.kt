package com.example.warehouse.orders.presentation.add_edit_order_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.orders.domain.model.NewOrder
import com.example.warehouse.orders.domain.model.OrderContent
import com.example.warehouse.orders.domain.use_case.OrderUseCases
import com.example.warehouse.orders.presentation.OrderEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditOrderViewModel
@Inject constructor(
    private val orderUseCases: OrderUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val customerIdState = mutableStateOf("")
    val employeeIdState = mutableStateOf("")

    val orderContentsState = mutableListOf<OrderContent>(OrderContent("", ""))
    val contentsCount = mutableStateOf<Int>(1)


    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.AddProductInCreateOrder -> {
                orderContentsState.add(OrderContent("", ""))
                contentsCount.value = orderContentsState.size
                println("Size: ${orderContentsState.size}")
            }
            is OrderEvent.CreateOrder -> {
                viewModelScope.launch {
                    try {
                        orderUseCases.createOrder(
                            NewOrder(
                                customerId = customerIdState.value.toInt(),
                                employeeId = employeeIdState.value.toInt(),
                                orderContents = orderContentsState
                            )
                        )
                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "Add order onEvent create err: " + e.message)
                    }
                }
            }
        }
    }
}