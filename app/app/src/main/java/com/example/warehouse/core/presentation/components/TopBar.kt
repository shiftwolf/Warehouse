package com.example.warehouse.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.warehouse.core.presentation.util.View


@Composable
fun TopBar(
    title: String, buttonText: String, hasButton: Boolean,
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    fun add(navBackStackEntry: NavBackStackEntry?, navController: NavController) {
        when (navBackStackEntry?.destination?.route?.substringBefore("{")) {
            View.Products.route -> {
                navController.navigate(View.AddEditProduct.route + "?productEan=-1")
            }
            View.Orders.route -> {
                navController.navigate(View.AddEditOrder.route + "?orderId=-1")
            }
            View.Customers.route -> {
                navController.navigate(View.AddEditCustomer.route + "?customerId=-1")
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        if (hasButton) {
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = { add(navBackStackEntry = navBackStackEntry, navController = navController) }
            ) {
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}