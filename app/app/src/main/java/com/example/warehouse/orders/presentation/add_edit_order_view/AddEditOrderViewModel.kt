package com.example.warehouse.orders.presentation.add_edit_order_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.orders.domain.model.NewOrder
import com.example.warehouse.orders.domain.model.OrderContent
import com.example.warehouse.orders.domain.model.OrderContentState
import com.example.warehouse.orders.domain.use_case.OrderUseCases
import com.example.warehouse.orders.presentation.OrderEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditOrderViewModel
@Inject constructor(
    private val orderUseCases: OrderUseCases,
    // savedStateHandle: SavedStateHandle
) : ViewModel() {

    val customerIdState = mutableStateOf("")
    val employeeIdState = mutableStateOf("")

    val orderContentsState = mutableStateListOf<OrderContentState>(
        OrderContentState(
            productEan = mutableStateOf(""),
            amount = mutableStateOf("")
        ))

    val contentsCount = mutableStateOf<Int>(1)


    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.AddProductInCreateOrder -> {
                orderContentsState.add(OrderContentState(mutableStateOf(""), mutableStateOf("")))
                contentsCount.value = orderContentsState.size
                println("Size: ${orderContentsState.size}")
            }
            is OrderEvent.CreateOrder -> {
                val orderContents: MutableList<OrderContent> = mutableListOf()
                for (content in event.orderContentsState) {
                    orderContents.add(OrderContent(
                        productEan = content.productEan.value,
                        amount = content.amount.value
                    ))
                }
                viewModelScope.launch {
                    try {
                        orderUseCases.createOrder(
                            NewOrder(
                                customerId = event.customerId,
                                employeeId = event.employeeId,
                                orderContents = orderContents
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