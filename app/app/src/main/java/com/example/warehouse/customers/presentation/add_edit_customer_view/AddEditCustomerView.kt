package com.example.warehouse.customers.presentation.add_edit_customer_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.core.presentation.util.View
import com.example.warehouse.customers.presentation.customers_view.CustomerEvent
import com.example.warehouse.customers.presentation.customers_view.CustomerViewModel
import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.presentation.add_edit_product_view.AddEditProductViewModel
import com.example.warehouse.products.presentation.products_view.ProductEvent
import com.example.warehouse.products.presentation.products_view.ProductViewModel

@Composable
fun AddEditCustomerView(
    navController: NavController,
    viewModel: AddEditCustomerViewModel = hiltViewModel(),
    customerViewModel: CustomerViewModel = hiltViewModel()
) {

    val nameField = viewModel.nameState
    val address1Field = viewModel.address1State
    val address2Field = viewModel.address2State
    val zipcodeField = viewModel.zipcodeState
    val cityField = viewModel.cityState
    val stateField = viewModel.stateState
    val countryField = viewModel.countryState

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ){

        OutlinedTextField(
            value = nameField.value,
            onValueChange = { nameField.value = it },
            label = { Text("Name") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = address1Field.value,
            onValueChange = { address1Field.value = it },
            label = { Text("Address 1") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = address2Field.value ?: "",
            onValueChange = { address2Field.value = it },
            label = { Text("Address 2") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = zipcodeField.value ?: "",
            onValueChange = { zipcodeField.value = it },
            label = { Text("ZIP Code") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = stateField.value ?: "",
            onValueChange = { stateField.value = it },
            label = { Text("State") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = countryField.value,
            onValueChange = { countryField.value = it },
            label = { Text("Country") },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = { navController.navigate(View.Customers.route) }
            ){
                Text("Cancel")
            }

            Spacer(Modifier.width(32.dp))

            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    if (viewModel.currentId != -1) {
                        println("customer view edit onClick: ${nameField.value}")
                        viewModel.onEvent(CustomerEvent.EditCustomer(
                            viewModel.currentId,
                            nameField.value,
                            address1Field.value,
                            address2Field.value,
                            zipcodeField.value,
                            cityField.value,
                            stateField.value,
                            countryField.value
                        ))
                    } else {
                        println("customer view create onClick: ${nameField.value}")
                        viewModel.onEvent(CustomerEvent.CreateCustomer(
                            nameField.value,
                            address1Field.value,
                            address2Field.value,
                            zipcodeField.value,
                            cityField.value,
                            stateField.value,
                            countryField.value
                        ))
                    }
                    navController.navigate(View.Customers.route)
                },
                enabled = true
            ){
                Text("Submit")
            }
        }
    }
}