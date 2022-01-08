package com.example.warehouse.customers.domain.repo

import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.products.domain.model.Product
import retrofit2.Response

interface CustomerRepo {

    suspend fun getCustomers(): Response<List<Customer>>

    suspend fun getCustomer(id: Int): Response<List<Customer>>

    suspend fun deleteCustomer(id: Int)

    suspend fun updateCustomer(
        id: Int,
        updatedName: String,
        updatedAddress1: String,
        updatedAddress2: String?,
        updatedZipcode: String?,
        updatedCity: String,
        updatedState: String?,
        updatedCountry: String
    )

    suspend fun createCustomer(
        name: String,
        address1: String,
        address2: String?,
        zipcode: String?,
        city: String,
        state: String?,
        country: String
    )

}