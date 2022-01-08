package com.example.warehouse.products.presentation.add_edit_product_view

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.domain.use_case.ProductUseCases
import com.example.warehouse.products.presentation.products_view.ProductEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditProductViewModel
@Inject constructor(
    private val productUseCases: ProductUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val nameState = mutableStateOf("")
    val eanState = mutableStateOf("")
    val amountState = mutableStateOf("")
    val locationState: MutableState<String?> = mutableStateOf("")

    var product = Product("", -1, "", "")
    var currentEan: String = ""

    init {
        savedStateHandle.get<String>("productEan")?.let { longEan ->
            val ean = longEan.substringAfter("=")
            currentEan = ean

            if (ean != "-1") {
                viewModelScope.launch {
                    try {
                        product = productUseCases.getProduct(ean)!!

                        nameState.value = product.name
                        eanState.value = product.ean
                        amountState.value = product.amount.toString()
                        locationState.value = product.location

                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "init err: " + e.message)
                    }
                }
            } else {
                nameState.value = ""
                eanState.value = ""
                amountState.value = ""
                locationState.value = ""
            }
        }
    }

    fun onEvent(event: ProductEvent) {
        when(event) {
            is ProductEvent.EditProduct -> {
                viewModelScope.launch {
                    try {
                        productUseCases.updateProduct(
                            event.ean,
                            event.updatedEan,
                            event.updatedName,
                            event.updatedAmount,
                            event.updatedLocation)
                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "Product onEvent edit err: " + e.message)
                    }
                }
            }
            is ProductEvent.CreateProduct -> {
                viewModelScope.launch {
                    try {
                        productUseCases.createProduct(
                            event.ean,
                            event.name,
                            event.amount,
                            event.location)
                    } catch (e: Throwable) {
                        Log.d(ContentValues.TAG, "Product onEvent create err: " + e.message)
                    }
                }
            }
        }
    }
}