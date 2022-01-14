package com.example.warehouse.settings.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.warehouse.WarehouseApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// dark mode functionality,
// change IP address & port functionality
// not implemented yet

@HiltViewModel
class SettingsViewModel
@Inject constructor() : ViewModel() {

    val isDarkMode = mutableStateOf<Boolean>(false)

    val ipAddressState = mutableStateOf<String>(WarehouseApp.Companion.ipAddress)
    val portState = mutableStateOf<String>(WarehouseApp.Companion.port)

}