package com.example.warehouse.z

data class OrderPreview(
    val id: Int,
    val completed: Int,
    val customer_id: Int,
    val employee_id: Int,
    val created_at: String
)