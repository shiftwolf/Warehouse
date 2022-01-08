package com.example.warehouse.products.domain.use_case

import com.example.warehouse.products.domain.repo.ProductRepo

class UpdateProductUseCase(
    private val repo: ProductRepo
) {
    suspend operator fun invoke(
        ean: String,
        updatedEan: String,
        updatedName: String,
        updatedAmount: Int,
        updatedLocation: String
    ) {
        repo.updateProduct(ean, updatedEan, updatedName, updatedAmount, updatedLocation)
    }
}