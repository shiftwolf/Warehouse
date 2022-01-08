package com.example.warehouse.orders.presentation.add_edit_order_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.core.presentation.util.View
import com.example.warehouse.orders.presentation.OrderEvent


@Composable
fun AddEditOrderView(
    navController: NavController,
    viewModel: AddEditOrderViewModel = hiltViewModel()
) {
    val customerIdField = viewModel.customerIdState
    val employeeIdField = viewModel.employeeIdState

    val orderContents = viewModel.orderContentsState

    val count = viewModel.contentsCount.value

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ){

        OutlinedTextField(
            value = customerIdField.value,
            onValueChange = { customerIdField.value = it },
            label = { Text("Customer ID") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = employeeIdField.value,
            onValueChange = { employeeIdField.value = it },
            label = { Text("Employee ID") },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text(text = "Products", fontWeight = FontWeight.Bold)

        LazyColumn {
            items(count) { index ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    OutlinedTextField(
                        value = orderContents[index].productEan,
                        onValueChange = {
                            println("ean: $orderContents[index].productEan")
                            orderContents[index].productEan = it },
                        label = { Text("Product EAN") },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = orderContents[index].amount.toString(),
                        onValueChange = { orderContents[index].amount = it },
                        label = { Text("Product Amount") },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        IconButton(
            onClick = { viewModel.onEvent(OrderEvent.AddProductInCreateOrder) },
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add product"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = { navController.navigate(View.Orders.route) }
            ){
                Text("Cancel")
            }

            Spacer(Modifier.width(32.dp))

            Button(
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    // TODO
                }
            ){
                Text("Submit")
            }
        }
    }
}
