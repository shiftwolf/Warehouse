package com.example.warehouse.z

data class Employee(
    val created_at: String,
    val firstname: String,
    val hashedpw: String,
    val id: Int,
    val lastname: String,
    val permissions: Int,
    val username: String
)