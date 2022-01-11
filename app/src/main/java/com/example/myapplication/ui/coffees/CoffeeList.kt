package com.example.myapplication.ui.coffees

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.myapplication.network.model.CoffeeModel

@Composable
fun ListOfCoffees(coffees: List<CoffeeModel>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(all = Dp(16F))
    ) {
        coffees.forEach { coffee ->
            Row {
                Text(text = coffee.toPrettyString())
            }
        }
    }
}