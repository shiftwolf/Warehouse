package com.example.warehouse.customers.domain.model

data class Customer(
    val id: Int,
    val name: String,
    val address1: String,
    val address2: String?,
    val zipcode: String?,
    val city: String,
    val state: String?,
    val country: String
)