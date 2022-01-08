package com.example.warehouse.orders.domain.use_case

import com.example.warehouse.orders.domain.model.OrderDetails
import com.example.warehouse.orders.domain.repo.OrderRepo

class GetOrderDetailsUseCase(
    private val repo: OrderRepo
) {

    suspend operator fun invoke(orderId: Int): OrderDetails? {
        return repo.getOrderDetails(orderId).body()?.first()
    }

}