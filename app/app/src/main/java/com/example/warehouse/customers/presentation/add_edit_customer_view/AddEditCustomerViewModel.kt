package com.example.warehouse.customers.presentation.add_edit_customer_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.customers.domain.use_case.CustomerUseCases
import com.example.warehouse.customers.presentation.customers_view.CustomerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCustomerViewModel
@Inject constructor(
    private val customerUseCases: CustomerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val nameState = mutableStateOf("")
    val address1State = mutableStateOf("")
    val address2State: MutableState<String?> = mutableStateOf("")
    val zipcodeState: MutableState<String?> = mutableStateOf("")
    val cityState = mutableStateOf("")
    val stateState: MutableState<String?> = mutableStateOf("")
    val countryState = mutableStateOf("")

    var customer = Customer(0, "", "", null, null, "", "", "")
    var currentId: Int = 0

    init {
        savedStateHandle.get<String>("customerId")?.let { longId ->
            val id = longId.substringAfter("=").toInt()
            currentId = id

            if (id != -1) {
                viewModelScope.launch {
                    try {
                        customer = customerUseCases.getCustomer(id)!!

                        nameState.value = customer.name
                        address1State.value = customer.address1
                        address2State.value = customer.address2
                        zipcodeState.value = customer.zipcode
                        cityState.value = customer.city
                        stateState.value = customer.state
                        countryState.value = customer.country

                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "Customer edit init err: " + e.message)
                    }
                }
            } else {
                nameState.value = ""
                address1State.value = ""
                address2State.value = ""
                zipcodeState.value = ""
                cityState.value = ""
                stateState.value = ""
                countryState.value = ""
            }
        }
    }

    fun onEvent(event: CustomerEvent) {
        when(event) {
            is CustomerEvent.EditCustomer -> {
                viewModelScope.launch {
                    try {
                        println("Customer edit event - ${event.updatedName}")
                        customerUseCases.updateCustomer(
                            currentId,
                            event.updatedName,
                            event.updatedAddress1,
                            event.updatedAddress2,
                            event.updatedZipcode,
                            event.updatedCity,
                            event.updatedState,
                            event.updatedCountry
                        )
                    } catch(e: Throwable) {
                        Log.d(ContentValues.TAG, "Customer onEvent edit err: " + e.message)
                    }
                }
            }
            is CustomerEvent.CreateCustomer -> {
                viewModelScope.launch {
                    try {
                        println("Customer create event - ${event.name}")
                        customerUseCases.createCustomer(
                            event.name, event.address1, event.address2, event.zipcode, event.city,
                            event.state, event.country
                        )
                    } catch(e: Throwable) {
                        Log.d(ContentValues.TAG, "Customer onEvent create err: " + e.message)
                    }
                }
            }
        }
    }

}