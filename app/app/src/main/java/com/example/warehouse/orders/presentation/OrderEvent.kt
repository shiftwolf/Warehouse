package com.example.warehouse.orders.presentation

import com.example.warehouse.orders.domain.model.NewOrder

sealed class OrderEvent {
    data class ChangeCompletedValue(
        val orderId: Int,
        val newValue: Int
        ): OrderEvent()

    object AddProductInCreateOrder: OrderEvent()

    data class CreateOrder(val newOrder: NewOrder): OrderEvent()
}