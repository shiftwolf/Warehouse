package com.example.warehouse.orders.presentation.order_details_view.components

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
import com.example.warehouse.core.presentation.util.View
import com.example.warehouse.orders.domain.model.OrderedProduct
import com.example.warehouse.orders.presentation.order_details_view.OrderDetailsViewModel

@Composable
fun OrderedProductCard(
    product: OrderedProduct,
    viewModel: OrderDetailsViewModel = hiltViewModel()
) {
    val attributes = listOf( "Name", "EAN", "Amount", "Location" )
    val values = listOf(
        product.name,
        product.ean,
        product.orderedAmount.toString(),
        product.location.toString() ?: ""
    )

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
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
        }
    }
}
