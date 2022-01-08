package com.example.warehouse.products.presentation.products_view

import com.example.warehouse.products.domain.model.Product

sealed class ProductEvent {
    data class DeleteProduct(val product: Product): ProductEvent()
    data class EditProduct(
        val ean: String,
        val updatedEan: String,
        val updatedName: String,
        val updatedAmount: Int,
        val updatedLocation: String
        ): ProductEvent()
    data class CreateProduct(
        val ean: String,
        val name: String,
        val amount: Int,
        val location: String
        ): ProductEvent()

}