package com.example.warehouse.products.domain.model

data class Product(
    val location: String?,
    val amount: Int,
    val ean: String,
    val name: String
)