package com.example.warehouse.orders.domain.repo

import com.example.warehouse.orders.domain.model.NewOrder
import com.example.warehouse.orders.domain.model.OrderDetails
import com.example.warehouse.orders.domain.model.OrderPreview
import retrofit2.Response

interface OrderRepo {

    suspend fun getOrderPreviews(): Response<List<OrderPreview>>

    suspend fun getOrderDetails(orderId: Int): Response<List<OrderDetails>>

    suspend fun updateOrderCompletedValue(orderId: Int, newValue: Int)

    suspend fun createOrder(newOrder: NewOrder)

    suspend fun deleteOrder(order: OrderPreview)

}