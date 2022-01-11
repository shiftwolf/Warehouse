package com.example.warehouse.products.presentation.add_edit_product_view

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
import com.example.warehouse.products.domain.model.Product
import com.example.warehouse.products.presentation.products_view.ProductEvent
import com.example.warehouse.products.presentation.products_view.ProductViewModel
import com.example.warehouse.core.presentation.util.View


@Composable
fun AddEditProductView(
    navController: NavController,
    viewModel: AddEditProductViewModel = hiltViewModel(),
    productViewModel: ProductViewModel = hiltViewModel()
) {

    val nameField = viewModel.nameState
    val eanField = viewModel.eanState
    val amountField = viewModel.amountState
    val locationField = viewModel.locationState

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
            value = eanField.value,
            onValueChange = { eanField.value = it },
            label = { Text("EAN") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = amountField.value,
            onValueChange = { amountField.value = it },
            label = { Text("Amount") },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            value = locationField.value ?: "",
            onValueChange = { locationField.value = it },
            label = { Text("Location") },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = { navController.navigate(View.Products.route) }
            ){
                Text("Cancel")
            }

            Spacer(Modifier.width(32.dp))

            Button(
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    if (viewModel.currentEan != "-1") {
                        viewModel.onEvent(ProductEvent.EditProduct(
                            viewModel.currentEan,
                            eanField.value,
                            nameField.value,
                            amountField.value.toInt(),
                            locationField.value ?: ""
                        ))
                    } else {
                        viewModel.onEvent(ProductEvent.CreateProduct(
                            eanField.value,
                            nameField.value,
                            amountField.value.toInt(),
                            locationField.value ?: ""
                        ))
                        productViewModel.products.add(Product(
                            ean = eanField.value,
                            name = nameField.value,
                            amount = amountField.value.toInt(),
                            location = locationField.value
                        ))
                    }

                    navController.navigate(View.Products.route)
                }
            ){
                Text("Submit")
            }
        }
    }
}