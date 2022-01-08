package com.example.warehouse.products.domain.use_case

import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.domain.repo.ProductRepo

class GetProductUseCase(
    private val repo: ProductRepo
) {

    suspend operator fun invoke(ean: String): Product? {
        return repo.getProduct(ean).body()?.first()
    }

}