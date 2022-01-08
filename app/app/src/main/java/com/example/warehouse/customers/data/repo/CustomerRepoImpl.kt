package com.example.warehouse.customers.data.repo

import com.example.warehouse.customers.data.source.CustomerRetrofitService
import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.customers.domain.repo.CustomerRepo
import retrofit2.Response

class CustomerRepoImpl(
    private val retrofit: CustomerRetrofitService
): CustomerRepo {

    override suspend fun getCustomers(): Response<List<Customer>> {
        return retrofit.getCustomers()
    }

    override suspend fun getCustomer(id: Int): Response<List<Customer>> {
        return retrofit.getCustomer(id)
    }

    override suspend fun deleteCustomer(id: Int) {
        retrofit.deleteCustomer(id)
    }

    override suspend fun updateCustomer(
        id: Int,
        updatedName: String,
        updatedAddress1: String,
        updatedAddress2: String?,
        updatedZipcode: String?,
        updatedCity: String,
        updatedState: String?,
        updatedCountry: String
    ) {
        retrofit.updateCustomer(id, updatedName, updatedAddress1, updatedAddress2, updatedZipcode,
            updatedCity, updatedState, updatedCountry)
    }

    override suspend fun createCustomer(
        name: String,
        address1: String,
        address2: String?,
        zipcode: String?,
        city: String,
        state: String?,
        country: String
    ) {
        retrofit.createCustomer(name, address1, address2, zipcode, city, state, country)
    }
}