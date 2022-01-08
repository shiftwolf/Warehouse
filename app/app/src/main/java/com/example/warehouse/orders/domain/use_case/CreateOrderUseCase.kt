package com.example.warehouse.orders.domain.use_case

import com.example.warehouse.orders.domain.model.NewOrder
import com.example.warehouse.orders.domain.repo.OrderRepo

class CreateOrderUseCase(
    private val repo: OrderRepo
) {

    suspend operator fun invoke(newOrder: NewOrder) {
        repo.createOrder(newOrder)
    }
}