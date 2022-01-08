package com.example.warehouse.customers.presentation.customers_view

import com.example.warehouse.customers.domain.model.Customer

sealed class CustomerEvent {
    data class DeleteCustomer(val customer: Customer): CustomerEvent()

    data class EditCustomer(
        val id: Int,
        val updatedName: String,
        val updatedAddress1: String,
        val updatedAddress2: String?,
        val updatedZipcode: String?,
        val updatedCity: String,
        val updatedState: String?,
        val updatedCountry: String
    ): CustomerEvent()

    data class CreateCustomer(
        val name: String,
        val address1: String,
        val address2: String?,
        val zipcode: String?,
        val city: String,
        val state: String?,
        val country: String
    ): CustomerEvent()
}
