package com.example.warehouse.orders.presentation.orders_view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.core.presentation.util.View
import com.example.warehouse.orders.domain.model.OrderPreview
import com.example.warehouse.orders.presentation.OrderEvent
import com.example.warehouse.orders.presentation.orders_view.OrderViewModel
import com.example.warehouse.products.presentation.products_view.ProductEvent


@ExperimentalMaterialApi
@Composable
fun OrderCard(
    order: OrderPreview,
    navController: NavController,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val attributes = listOf( "Order ID", "Customer", "Employee" )
    val values = listOf(
        order.orderId.toString(),
        order.customerName,
        order.employeeName
    )

    val completedState = remember { mutableStateOf(order.completed != 0) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = completedState.value,
            onCheckedChange = {
                completedState.value = it
                viewModel.onEvent(OrderEvent.ChangeCompletedValue(
                    order.orderId,
                    viewModel.boolToInt(completedState.value)
                ))
                println(order.orderId)
                println(viewModel.boolToInt(completedState.value))
            }
        )
        
        Spacer(modifier = Modifier.width(6.dp))

        Card(
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    View.OrderDetails.route + "?orderId=${order.orderId}")
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Column(
                    modifier = Modifier.weight(2.3f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    attributes.forEach{ attribute -> Text(text = attribute) }
                }

                Column(
                    modifier = Modifier.weight(4f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    values.forEach{ value ->
                        Text(text = value ?: "")
                    }
                }


                Column(
                    modifier = Modifier
                        .weight(2.5f)
                        .padding(2.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            navController.navigate(
                                View.OrderDetails.route + "?orderId=${order.orderId}")
                        },
                        modifier = Modifier.defaultMinSize(90.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Details")
                    }
                    Button(
                        onClick = {
                            viewModel.onEvent(OrderEvent.DeleteOrder(order))
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
}
