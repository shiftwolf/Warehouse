package com.example.warehouse.products.data.source

import com.example.warehouse.products.domain.model.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProductRetrofitService {

    @GET("apiv1/products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("apiv1/products/{ean}")
    suspend fun getProduct(@Path("ean") ean: String): Response<List<Product>>

    @DELETE("apiv1/products/{ean}")
    suspend fun deleteProduct(@Path("ean") ean: String)

    @PUT("apiv1/products/{ean}")
    suspend fun updateProduct(
        @Path("ean") ean: String,
        @Query("ean") updatedEan: String,
        @Query("name") updatedName: String,
        @Query("amount") updatedAmount: Int,
        @Query("location") updatedLocation: String?
    )

    @POST("apiv1/products")
    suspend fun createProduct(
        @Query("ean") ean: String,
        @Query("name") name: String,
        @Query("amount") amount: Int,
        @Query("location") location: String?
    )
}