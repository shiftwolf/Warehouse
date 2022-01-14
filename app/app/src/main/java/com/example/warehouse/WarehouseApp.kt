package com.example.warehouse

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

// "http://192.168.178.25:3000/"

@HiltAndroidApp
class WarehouseApp : Application() {
    companion object {
        var ipAddress: String = "192.168.178.25"
        var port: String = "3000"
        var baseUrl: String = "http://${ipAddress}:${port}/"
    }
}