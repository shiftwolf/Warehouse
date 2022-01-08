package com.example.warehouse.products.domain.use_case

import com.example.warehouse.products.domain.repo.ProductRepo

class CreateProductUseCase(
    private val repo: ProductRepo
) {

    suspend operator fun invoke(ean: String, name: String, amount: Int, location: String) {

        repo.createProduct(ean, name, amount, location)

    }

}