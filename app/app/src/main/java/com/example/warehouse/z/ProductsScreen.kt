package com.example.warehouse.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.http.GET


@Composable
fun ProductsScreen() {

}

@Preview(showBackground = true)
@Composable
fun ProductCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(12.dp),
        //elevation = 10.dp
    ) {
        Column() {
            Row(modifier = Modifier.padding(start = 16.dp, top = 18.dp, bottom = 10.dp)) {
                Text(
                    text = "Name:",
                    modifier = Modifier.width(70.dp)
                )
                Text(
                    text = "Persil",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(start = 16.dp, bottom = 10.dp)) {
                Text(
                    text = "EAN:",
                    modifier = Modifier.width(70.dp)
                )
                Text(
                    text = "1234567890123",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(start = 16.dp, bottom = 18.dp)) {
                Text(
                    text = "Location:",
                    modifier = Modifier.width(70.dp)
                )
                Text(
                    text = "Example Location",
                    fontWeight = FontWeight.Bold
                )
            }
        }



    }
}