package com.example.warehouse.products.domain.use_case

data class ProductUseCases(
    val getProducts: GetProductsUseCase,
    val getProduct: GetProductUseCase,
    val deleteProduct: DeleteProductUseCase,
    val updateProduct: UpdateProductUseCase,
    val createProduct: CreateProductUseCase
)
