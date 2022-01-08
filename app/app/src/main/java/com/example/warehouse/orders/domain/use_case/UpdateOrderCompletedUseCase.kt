package com.example.warehouse.orders.domain.use_case

import com.example.warehouse.orders.domain.repo.OrderRepo

class UpdateOrderCompletedUseCase(
    private val repo: OrderRepo
) {
    suspend operator fun invoke(orderId: Int, newValue: Int) {
        repo.updateOrderCompletedValue(orderId, newValue)
    }

}