package com.example.warehouse.customers.presentation.customers_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.customers.domain.use_case.CustomerUseCases
import com.example.warehouse.products.domain.use_case.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel
@Inject constructor(
    private val customerUseCases: CustomerUseCases
) : ViewModel() {

    val customers = mutableStateListOf<Customer>()

    init {
        viewModelScope.launch {
            try {
                customerUseCases.getCustomers()!!.let { customers.addAll(it) }
            } catch (e : Throwable) {
                Log.d(ContentValues.TAG, "CustomerViewModel err: " + e.message)
            }
        }
    }

    fun onEvent(event: CustomerEvent) {
        if (event is CustomerEvent.DeleteCustomer) {
            viewModelScope.launch {
                try {
                    customers.remove(event.customer)
                    customerUseCases.deleteCustomer(event.customer.id)
                } catch (e: Throwable) {
                    Log.d(ContentValues.TAG, "Customer onEvent delete err: " + e.message)
                }
            }
        }
    }

}