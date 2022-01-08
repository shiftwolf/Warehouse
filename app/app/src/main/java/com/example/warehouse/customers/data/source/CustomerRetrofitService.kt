package com.example.warehouse.customers.data.source

import com.example.warehouse.customers.domain.model.Customer
import retrofit2.Response
import retrofit2.http.*

interface CustomerRetrofitService {

    @GET("apiv1/customers")
    suspend fun getCustomers(): Response<List<Customer>>

    @GET("apiv1/customers/{id}")
    suspend fun getCustomer(@Path("id") id: Int): Response<List<Customer>>

    @DELETE("apiv1/customers/{id}")
    suspend fun deleteCustomer(@Path("id") id: Int)

    @PUT("apiv1/customers/{id}")
    suspend fun updateCustomer(
        @Path("id") id: Int,
        @Query("name") updatedName: String,
        @Query("address1") updatedAddress1: String,
        @Query("address2") updatedAddress2: String?,
        @Query("zipcode") updatedZipCode: String?,
        @Query("city") updatedCity: String,
        @Query("state") updatedState: String?,
        @Query("country") updatedCountry: String,
    )

    @POST("apiv1/customers")
    suspend fun createCustomer(
        @Query("name") name: String,
        @Query("address1") address1: String,
        @Query("address2") address2: String?,
        @Query("zipcode") zipCode: String?,
        @Query("city") city: String,
        @Query("state") state: String?,
        @Query("country") country: String,
    )

}