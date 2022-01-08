package com.example.warehouse.customers.domain.use_case

import com.example.warehouse.products.domain.use_case.*

data class CustomerUseCases (
    val getCustomers: GetCustomersUseCase,
    val getCustomer: GetCustomerUseCase,
    val deleteCustomer: DeleteCustomerUseCase,
    val updateCustomer: UpdateCustomerUseCase,
    val createCustomer: CreateCustomerUseCase
    )