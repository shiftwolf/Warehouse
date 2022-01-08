package com.example.warehouse.orders.domain.model

data class Order(
    val completed: Int,
    val created_at: String,
    val id: Int
)