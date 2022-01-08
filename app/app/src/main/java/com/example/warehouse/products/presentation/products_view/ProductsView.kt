package com.example.warehouse.products.presentation.products_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.products.presentation.products_view.components.ProductCard


// TODO:
// add font styles to Type.kt
// add rounded corners style to Shape.kt
// add colors to Color.kt (topBar bg color)
// add icons


@Composable
fun ProductsView (
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val products = viewModel.products

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductCard(product, navController)
            }
        }

}
