package com.example.warehouse.orders.presentation.order_details_view

import android.icu.text.IDNA
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.warehouse.orders.domain.model.OrderedProduct
import com.example.warehouse.orders.presentation.order_details_view.components.InfoCard
import com.example.warehouse.orders.presentation.order_details_view.components.OrderedProductCard
import com.example.warehouse.orders.presentation.order_details_view.components.ProductsInfoCard


@Composable
fun OrderDetailsView(
    navController: NavController,
    viewModel: OrderDetailsViewModel = hiltViewModel()
) {

    val orderAttributes = listOf<String>( "ID", "Created at", "Assigned Employee" )
    val orderValues = listOf<String>(
        viewModel.currentId.toString(),
        viewModel.orderDetails.order.created_at,
        viewModel.orderDetails.employeeName ?: ""
    )

    val customerAttributes = listOf<String>( "ID", "Name", "Address 1", "Address 2", "ZIP Code",
        "City", "State", "Country")
    val customerValues = listOf<String>(
        viewModel.orderDetails.customer.id.toString(),
        viewModel.orderDetails.customer.name,
        viewModel.orderDetails.customer.address1,
        viewModel.orderDetails.customer.address2 ?: "",
        viewModel.orderDetails.customer.zipcode ?: "",
        viewModel.orderDetails.customer.city,
        viewModel.orderDetails.customer.state ?: "",
        viewModel.orderDetails.customer.country
    )

    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        InfoCard(orderAttributes, orderValues, "Order")

        InfoCard(customerAttributes, customerValues, "Customer")

        ProductsInfoCard(viewModel.orderDetails.orderedProducts, "Product")

    }
}


