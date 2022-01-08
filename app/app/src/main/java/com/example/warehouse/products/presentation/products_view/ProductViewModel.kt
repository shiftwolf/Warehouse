package com.example.warehouse.products.presentation.products_view

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehouse.core.presentation.MainActivity
import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.domain.use_case.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel
@Inject constructor(
    private val productUseCases: ProductUseCases
) : ViewModel() {

    val products = mutableStateListOf<Product>()

    init {
        viewModelScope.launch {
            try {
                productUseCases.getProducts()!!.let { products.addAll(it) }
            } catch(e : Throwable) {
                Log.d(TAG, "ProductViewModel err: " + e.message)
            }
        }
    }

    fun onEvent(event: ProductEvent) {
        if (event is ProductEvent.DeleteProduct) {
            viewModelScope.launch {
                try {
                    products.remove(event.product)
                    productUseCases.deleteProduct(event.product.ean)
                } catch (e: Throwable) {
                    Log.d(ContentValues.TAG, "Product onEvent delete err: " + e.message)
                }
            }
        }
    }
}