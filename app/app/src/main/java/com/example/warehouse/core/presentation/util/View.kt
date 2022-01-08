package com.example.warehouse.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.ui.graphics.vector.ImageVector


sealed class View(var route: String, val icon: ImageVector, val title: String) {
    object Products: View("products_view", Icons.Filled.ViewInAr, "Products")
    object Orders: View("orders_view", Icons.Filled.LocalShipping, "Orders")
    object Customers: View("customers_view", Icons.Filled.EmojiPeople, "Customers")
    object Settings: View("settings_view", Icons.Filled.Settings, "Settings")
    object AddEditProduct: View("add_edit_product_view", Icons.Filled.Settings, "")
    object AddEditCustomer: View("add_edit_customer_view", Icons.Filled.Settings, "")
    object AddEditOrder: View("add_edit_order_view", Icons.Filled.Settings, "")
    object OrderDetails: View("order_details_view", Icons.Filled.Settings, "")
}
