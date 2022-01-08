package com.example.warehouse.customers.presentation.customers_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.customers.presentation.customers_view.components.CustomerCard


@Composable
fun CustomersView (
    navController: NavController,
    viewModel: CustomerViewModel = hiltViewModel(),
) {
    val customers = viewModel.customers

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(customers) { customer ->
            CustomerCard(customer, navController)
        }
    }

}