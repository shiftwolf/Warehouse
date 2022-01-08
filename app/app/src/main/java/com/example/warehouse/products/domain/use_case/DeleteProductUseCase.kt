package com.example.warehouse.products.domain.use_case

import com.example.warehouse.products.domain.repo.ProductRepo

class DeleteProductUseCase(
    private val repo: ProductRepo
) {

    suspend operator fun invoke(ean: String) {
        repo.deleteProduct(ean)
    }

}