package com.example.warehouse.core.presentation.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.warehouse.products.presentation.add_edit_product_view.AddEditProductView
import com.example.warehouse.products.presentation.products_view.ProductsView
import com.example.warehouse.core.presentation.util.View
import com.example.warehouse.customers.presentation.add_edit_customer_view.AddEditCustomerView
import com.example.warehouse.customers.presentation.customers_view.CustomersView
import com.example.warehouse.orders.presentation.add_edit_order_view.AddEditOrderView
import com.example.warehouse.orders.presentation.order_details_view.OrderDetailsView
import com.example.warehouse.orders.presentation.orders_view.OrdersView

@ExperimentalMaterialApi
@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = View.Orders.route
    ) {
        composable(View.Products.route) {
            ProductsView(navController = navHostController)
        }
        composable(View.Orders.route) {
            OrdersView(navController = navHostController)
        }
        composable(View.Customers.route) {
            CustomersView(navController = navHostController)
        }
        composable(View.Settings.route) {

        }
        composable(
            route = View.AddEditProduct.route + "{productEan}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("productEan")
                ?.let { AddEditProductView(navHostController) }
        }
        composable(
            route = View.AddEditCustomer.route + "{customerId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("customerId")
                ?.let { AddEditCustomerView(navHostController) }
        }
        composable(
            route = View.OrderDetails.route + "{orderId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("orderId")
                ?.let { OrderDetailsView(navHostController) }
        }
        composable(
            route = View.AddEditOrder.route + "{orderId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("orderId")
                ?.let { AddEditOrderView(navHostController) }
        }
    }
}

