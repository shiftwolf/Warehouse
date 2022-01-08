package com.example.warehouse.orders.domain.model

import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.products.domain.model.Product

data class OrderDetails(
    val customer: Customer,
    val employeeName: String?,
    val order: Order,
    val orderedProducts: List<OrderedProduct>
)

