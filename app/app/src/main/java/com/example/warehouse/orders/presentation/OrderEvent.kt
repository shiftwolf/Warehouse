package com.example.warehouse.orders.presentation

import com.example.warehouse.orders.domain.model.OrderContentState
import com.example.warehouse.orders.domain.model.OrderPreview

sealed class OrderEvent {
    data class ChangeCompletedValue(
        val orderId: Int,
        val newValue: Int
        ): OrderEvent()

    object AddProductInCreateOrder: OrderEvent()

    data class CreateOrder(
        val customerId: Int,
        val employeeId: Int,
        val orderContentsState: List<OrderContentState>
        ): OrderEvent()

    data class DeleteOrder(val order: OrderPreview): OrderEvent()
}