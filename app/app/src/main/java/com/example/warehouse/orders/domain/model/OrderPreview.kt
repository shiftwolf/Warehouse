package com.example.warehouse.orders.domain.model

data class OrderPreview (
    val orderId : Int,
    val createdAt : String,
    val completed : Int,
    val customerName : String,
    val customerId : Int,
    val employeeName : String?
)
