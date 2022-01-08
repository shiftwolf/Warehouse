package com.example.warehouse.orders.domain.model

data class OrderedProduct (
    val orderedAmount: Int,
    val ean: String,
    val location: String?,
    val name: String
)