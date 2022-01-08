package com.example.warehouse.orders.presentation.order_details_view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InfoCard(
    attributes: List<String>,
    values: List<String>,
    objectType: String
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "$objectType Details", fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.height(IntrinsicSize.Min)) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    attributes.forEach { attribute ->
                        Text(text = "$attribute:")
                    }
                }

                Column(
                    modifier = Modifier.weight(2f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    values.forEach { value ->
                        Text(text = value ?: "")
                    }
                }
            }
        }
    }
}
