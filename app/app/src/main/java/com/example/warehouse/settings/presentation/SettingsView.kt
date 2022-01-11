package com.example.warehouse.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
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


@Preview(showBackground = true)
@Composable
fun SettingsView() {

    val isChecked = remember { mutableStateOf<Boolean>(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),

    ){
        Text(text = "Dark Mode", fontWeight = FontWeight.Bold)
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