package com.example.warehouse.customers.domain.use_case

import com.example.warehouse.customers.domain.repo.CustomerRepo

class DeleteCustomerUseCase(
    private val repo: CustomerRepo
) {
    suspend operator fun invoke(id: Int) {
        repo.deleteCustomer(id)
    }
}