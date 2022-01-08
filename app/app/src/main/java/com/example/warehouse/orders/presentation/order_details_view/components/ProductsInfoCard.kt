package com.example.warehouse.orders.presentation.order_details_view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.warehouse.orders.domain.model.OrderedProduct


@Composable
fun ProductsInfoCard(
    orderedProducts: List<OrderedProduct>,
    objectType: String
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text ="$objectType Details", fontWeight = FontWeight.Bold)

            orderedProducts.forEach{ product ->
                OrderedProductCard(product = product)
            }
        }
    }
}