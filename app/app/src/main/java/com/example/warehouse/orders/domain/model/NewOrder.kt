package com.example.warehouse.orders.domain.model

import com.google.gson.annotations.SerializedName

data class NewOrder (
    @SerializedName("customerID")
    val customerId: Int,

    @SerializedName("employeeID")
    val employeeId: Int,

    @SerializedName("contents")
    val orderContents: List<OrderContent>
)

data class OrderContent(
    @SerializedName("productEAN")
    var productEan: String,

    @SerializedName("amount")
    var amount: String
)