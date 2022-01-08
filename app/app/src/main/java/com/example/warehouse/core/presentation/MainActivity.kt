package com.example.warehouse.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.warehouse.ui.theme.WarehouseTheme
import com.example.warehouse.core.presentation.components.BottomNav
import com.example.warehouse.core.presentation.components.Navigation
import com.example.warehouse.core.presentation.util.View
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarehouseTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    val currentView = remember { mutableStateOf("") }
                    val visibility = remember { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    when (navBackStackEntry?.destination?.route?.substringBefore("{")) {
                        View.Products.route -> {
                            currentView.value = "Products"
                            visibility.value = true
                        }
                        View.Orders.route -> {
                            currentView.value = "Orders"
                            visibility.value = true
                        }
                        View.Customers.route -> {
                            currentView.value = "Customers"
                            visibility.value = true
                        }
                        View.Settings.route -> {
                            currentView.value = "Settings"
                            visibility.value = false
                        }
                        View.AddEditProduct.route -> {
                            currentView.value = "Edit"
                            visibility.value = false
                        }
                        View.AddEditCustomer.route -> {
                            currentView.value = "Edit"
                            visibility.value = false
                        }
                        View.AddEditOrder.route -> {
                            currentView.value = "Create"
                            visibility.value = false
                        }
                        View.OrderDetails.route -> {
                            currentView.value = "Details"
                            visibility.value = false
                        }
                    }

                    Scaffold(
                        topBar = { TopAppBar(title = { Text(currentView.value) }) },
                        floatingActionButton = {
                            if (visibility.value) {
                                FloatingActionButton(
                                    onClick = { add(navBackStackEntry, navController) }
                                ) {
                                    Icon(Icons.Filled.Add,"")
                            }
                        } },
                        floatingActionButtonPosition = FabPosition.Center,
                        bottomBar = { BottomNav(navController) }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.padding(
                            PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
                        )) {
                            Navigation(navHostController = navController)
                        }
                    }
                }
            }
        }
    }

    private fun add(navBackStackEntry: NavBackStackEntry?, navController: NavController) {
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

}

