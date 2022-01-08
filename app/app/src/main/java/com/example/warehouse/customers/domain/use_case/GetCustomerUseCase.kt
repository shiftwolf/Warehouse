package com.example.warehouse.customers.domain.use_case

import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.customers.domain.repo.CustomerRepo

class GetCustomerUseCase(
    private val repo: CustomerRepo
) {

    suspend operator fun invoke(id: Int): Customer? {
        return repo.getCustomer(id).body()?.first()
    }

}