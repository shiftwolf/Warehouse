package com.example.warehouse.orders.presentation.order_details_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.orders.domain.model.Order
import com.example.warehouse.orders.domain.model.OrderDetails
import com.example.warehouse.orders.domain.model.OrderedProduct
import com.example.warehouse.orders.domain.use_case.OrderUseCases
import com.example.warehouse.products.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel
@Inject constructor(
    private val orderUseCases: OrderUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var orderDetails = OrderDetails(
        customer = Customer(0, "", "", "", "", "", "", ""),
        employeeName = "",
        order = Order(0, "", 0),
        orderedProducts = mutableStateListOf<OrderedProduct>()
    )

    var currentId: Int = 0

    init {
        savedStateHandle.get<String>("orderId")?.let { longId ->
            val id = longId.substringAfter("=").toInt()
            currentId = id

            viewModelScope.launch {
                try {
                    println("OrderDetailsView init")
                    orderDetails = orderUseCases.getOrderDetails(currentId)!!
                } catch(e : Throwable) {
                    Log.d(ContentValues.TAG, "OrderDetailsViewModel init err: " + e.message)
                }
            }
        }
    }
}