package com.example.warehouse.customers.domain.use_case

import com.example.warehouse.customers.domain.repo.CustomerRepo

class UpdateCustomerUseCase(
    private val repo: CustomerRepo
) {

    suspend operator fun invoke(
        id: Int,
        updatedName: String,
        updatedAddress1: String,
        updatedAddress2: String?,
        updatedZipcode: String?,
        updatedCity: String,
        updatedState: String?,
        updatedCountry: String
    ) {

        repo.updateCustomer(id, updatedName, updatedAddress1, updatedAddress2, updatedZipcode,
            updatedCity, updatedState, updatedCountry)

    }

}