package com.example.warehouse.products.domain.use_case

import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.domain.repo.ProductRepo

class GetProductsUseCase(
    private val repo: ProductRepo
) {

    suspend operator fun invoke(): List<Product>? {
        return repo.getProducts().body()
    }

}