package com.example.warehouse.products.data.repo

import com.example.warehouse.products.data.source.ProductRetrofitService
import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.domain.repo.ProductRepo
import retrofit2.Response

class ProductRepoImpl(
    private val retrofit: ProductRetrofitService
) : ProductRepo{

    override suspend fun getProducts(): Response<List<Product>> {
        return retrofit.getProducts()
    }

    override suspend fun getProduct(ean: String): Response<List<Product>> {
        return retrofit.getProduct(ean)
    }

    override suspend fun deleteProduct(ean: String) {
        retrofit.deleteProduct(ean)
    }

    override suspend fun updateProduct(
        ean: String,
        updatedEan: String,
        updatedName: String,
        updatedAmount: Int,
        updatedLocation: String?
    ) {
        retrofit.updateProduct(ean, updatedEan, updatedName, updatedAmount, updatedLocation)
    }

    override suspend fun createProduct(ean: String, name: String, amount: Int, location: String?) {
        retrofit.createProduct(ean, name, amount, location)
    }

}