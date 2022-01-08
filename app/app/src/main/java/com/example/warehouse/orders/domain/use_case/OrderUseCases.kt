package com.example.warehouse.orders.domain.use_case

data class OrderUseCases (
    val getOrderPreviews: GetOrderPreviewsUseCase,
    val updateOrderCompletedValue: UpdateOrderCompletedUseCase,
    val getOrderDetails: GetOrderDetailsUseCase,
    val createOrder: CreateOrderUseCase
    )