package com.example.warehouse.products.presentation.products_view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
fun ProductCard(
    product: Product,
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val attributes = listOf( "Name", "EAN", "Amount", "Location" )
    val values = listOf(
        product.name,
        product.ean,
        product.amount.toString(),
        product.location.toString() ?: ""
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
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                attributes.forEach{ attribute ->
                    Text(text = "${attribute}:")
                }
            }

            Column(
                modifier = Modifier.weight(4f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                values.forEach{ value ->
                    Text(text = value ?: "")
                }
            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(2.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(
                            View.AddEditProduct.route +
                                "?productEan=${product.ean}")
                    },
                    modifier = Modifier.defaultMinSize(90.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Edit")
                }

                Button(
                    onClick = {
                        viewModel.onEvent(ProductEvent.DeleteProduct(product))
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
