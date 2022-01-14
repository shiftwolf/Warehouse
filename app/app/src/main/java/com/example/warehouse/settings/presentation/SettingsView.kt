package com.example.warehouse.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehouse.WarehouseApp


@Composable
fun SettingsView(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val isChecked = viewModel.isDarkMode

    Column(
        modifier = Modifier.padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Server",
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.ipAddressState.value,
                onValueChange = { viewModel.ipAddressState.value = it },
                label = { Text("IP Address") },
                shape = RoundedCornerShape(16.dp)
            )
            Button(
                onClick = { WarehouseApp.Companion.ipAddress = viewModel.ipAddressState.value },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Save")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.portState.value,
                onValueChange = { viewModel.portState.value = it },
                label = { Text("Port") },
                shape = RoundedCornerShape(16.dp)
            )
            Button(
                onClick = { WarehouseApp.Companion.port = viewModel.portState.value },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Save")
            }
        }
        
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Appearance", fontWeight = FontWeight.Bold)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
        ){
            Text(text = "Dark Mode")
            IconToggleButton(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it }
            ) {
                Icon(
                    imageVector = if (isChecked.value) Icons.Filled.ToggleOn else Icons.Filled.ToggleOff,
                    contentDescription = null
                )
            }
        }
    }
}