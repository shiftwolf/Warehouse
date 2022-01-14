package com.example.warehouse.orders.domain.use_case

import com.example.warehouse.orders.domain.model.OrderPreview
import com.example.warehouse.orders.domain.repo.OrderRepo

class DeleteOrderUseCase(
    private val repo: OrderRepo
) {
    suspend operator fun invoke(order: OrderPreview) {
        repo.deleteOrder(order)
    }

}