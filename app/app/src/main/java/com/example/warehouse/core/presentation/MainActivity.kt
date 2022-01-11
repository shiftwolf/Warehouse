package com.example.warehouse.core.presentation

import android.app.Application
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
import com.example.warehouse.core.presentation.components.TopBar
import com.example.warehouse.core.presentation.util.View
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarehouseTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    val currentView = remember { mutableStateOf("") }
                    val buttonText = remember { mutableStateOf("") }
                    val buttonVisibility = remember { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    when (navBackStackEntry?.destination?.route?.substringBefore("{")) {
                        View.Products.route -> {
                            currentView.value = "Products"
                            buttonText.value = "Add Product"
                            buttonVisibility.value = true
                        }
                        View.Orders.route -> {
                            currentView.value = "Orders"
                            buttonText.value = "Add Order"
                            buttonVisibility.value = true
                        }
                        View.Customers.route -> {
                            currentView.value = "Customers"
                            buttonText.value = "Add Customer"
                            buttonVisibility.value = true
                        }
                        View.Settings.route -> {
                            currentView.value = "Settings"
                            buttonVisibility.value = false
                        }
                        View.AddEditProduct.route -> {
                            currentView.value = "Edit Product"
                            buttonVisibility.value = false
                        }
                        View.AddEditCustomer.route -> {
                            currentView.value = "Edit Customer"
                            buttonVisibility.value = false
                        }
                        View.AddEditOrder.route -> {
                            currentView.value = "Create Order"
                            buttonVisibility.value = false
                        }
                        View.OrderDetails.route -> {
                            currentView.value = "Details"
                            buttonVisibility.value = false
                        }
                    }

                    Scaffold(
                        topBar = {
                             TopBar(
                                 title = currentView.value,
                                 buttonText = buttonText.value,
                                 hasButton = buttonVisibility.value,
                                 navController = navController
                             )
                                 },
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

}

