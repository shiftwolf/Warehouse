package com.example.warehouse.orders.data.repo

import com.example.warehouse.orders.data.source.OrderRetrofitService
import com.example.warehouse.orders.domain.model.NewOrder
import com.example.warehouse.orders.domain.model.OrderDetails
import com.example.warehouse.orders.domain.model.OrderPreview
import com.example.warehouse.orders.domain.repo.OrderRepo
import retrofit2.Response

class OrderRepoImpl(
    private val retrofit: OrderRetrofitService
): OrderRepo {

    override suspend fun getOrderPreviews(): Response<List<OrderPreview>> {
        return retrofit.getOrderPreviews()
    }

    override suspend fun getOrderDetails(orderId: Int): Response<List<OrderDetails>> {
        return retrofit.getOrderDetails(orderId)
    }

    override suspend fun updateOrderCompletedValue(orderId: Int, newValue: Int) {
        retrofit.updateOrderCompletedValue(orderId, newValue)
    }

    override suspend fun createOrder(newOrder: NewOrder) {
        retrofit.createOrder(newOrder)
    }
}