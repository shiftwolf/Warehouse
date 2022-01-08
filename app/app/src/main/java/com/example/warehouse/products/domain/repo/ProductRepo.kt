package com.example.warehouse.products.domain.repo

import com.example.warehouse.products.domain.model.Product
import retrofit2.Response

interface ProductRepo {

    suspend fun getProducts(): Response<List<Product>>

    suspend fun getProduct(ean: String): Response<List<Product>>

    suspend fun deleteProduct(ean: String)

    suspend fun updateProduct(
        ean: String,
        updatedEan: String,
        updatedName: String,
        updatedAmount: Int,
        updatedLocation: String?)

    suspend fun createProduct(
        ean: String,
        name: String,
        amount: Int,
        location: String?
    )

}