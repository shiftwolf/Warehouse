package com.example.warehouse.customers.domain.use_case

import com.example.warehouse.customers.domain.repo.CustomerRepo

class CreateCustomerUseCase(
    private val repo: CustomerRepo
) {

    suspend operator fun invoke(
        name: String,
        address1: String,
        address2: String?,
        zipcode: String?,
        city: String,
        state: String?,
        country: String
    ) {

        repo.createCustomer(name, address1, address2, zipcode, city, state, country)

    }

}