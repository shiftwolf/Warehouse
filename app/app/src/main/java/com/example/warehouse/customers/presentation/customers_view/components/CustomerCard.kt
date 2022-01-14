package com.example.warehouse.customers.presentation.customers_view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.core.presentation.util.View
import com.example.warehouse.customers.domain.model.Customer
import com.example.warehouse.customers.presentation.customers_view.CustomerEvent
import com.example.warehouse.customers.presentation.customers_view.CustomerViewModel


@Composable
fun CustomerCard(
    customer: Customer,
    navController: NavController,
    viewModel: CustomerViewModel = hiltViewModel()
) {
    val attributes = listOf( "ID", "Name", "Address 1", "Address 2", "ZIP Code", "City", "State", "Country" )
    val values = listOf(
        customer.id.toString(),
        customer.name,
        customer.address1,
        customer.address2,
        customer.zipcode,
        customer.city,
        customer.state,
        customer.country
    )

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier.weight(2.3f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                attributes.forEach{ attribute ->
                    Text(text = "${attribute}:")
                }
            }

            Column(
                modifier = Modifier.weight(4f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                values.forEach{ value ->
                    Text(text = value?: "")
                }
            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(2.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(View.AddEditCustomer.route +
                                "?customerId=${customer.id}")
                    },
                    modifier = Modifier.defaultMinSize(90.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Edit")
                }

                Button(
                    onClick = {
                        println("onClick delete customer")
                        viewModel.onEvent(CustomerEvent.DeleteCustomer(customer))
                    },
                    modifier = Modifier.defaultMinSize(90.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Delete")
                }
            }
        }
    }
}
