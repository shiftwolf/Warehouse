package com.example.warehouse.orders.presentation.orders_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.orders.domain.model.OrderPreview
import com.example.warehouse.orders.domain.use_case.OrderUseCases
import com.example.warehouse.orders.presentation.OrderEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel
@Inject constructor(
    private val orderUseCases: OrderUseCases
) : ViewModel() {

    val orders = mutableStateListOf<OrderPreview>()

    init {
        viewModelScope.launch {
            try {
                orderUseCases.getOrderPreviews()!!.let { orders.addAll(it) }
            } catch(e : Throwable) {
                Log.d(ContentValues.TAG, "OrderViewModel init err: " + e.message)
            }
        }
    }

    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.ChangeCompletedValue -> {
                viewModelScope.launch {
                    try {
                        orderUseCases.updateOrderCompletedValue(event.orderId, event.newValue)
                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "Order onEvent changeCompleted err: " + e.message)
                    }
                }
            }
            is OrderEvent.DeleteOrder -> {
                viewModelScope.launch {
                    try {
                        orders.remove(event.order)
                        orderUseCases.deleteOrder(event.order)
                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "Order onEvent delete err: " + e.message)
                    }
                }
            }
        }
    }

    fun boolToInt(bool: Boolean): Int{
        return if (bool) 1 else 0
    }

}