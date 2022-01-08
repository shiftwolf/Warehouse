package com.example.warehouse.customers.domain.use_case

import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.customers.domain.repo.CustomerRepo

class GetCustomersUseCase(
    private val repo: CustomerRepo
) {

    suspend operator fun invoke(): List<Customer>? {
        return repo.getCustomers().body()
    }

}