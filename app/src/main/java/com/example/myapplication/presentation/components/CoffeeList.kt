package com.example.myapplication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.myapplication.domain.models.CoffeeModel

@Composable
fun CoffeeList(coffees: List<CoffeeModel>, onClickNavigateToDetails: () -> Unit){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(all = Dp(16F))
    ) {
        coffees.forEach { coffee ->
            Row(
                modifier = Modifier.clickable {
                    onClickNavigateToDetails()
                }
            ) {
                Text(text = coffee.toPrettyString())
            }
        }
    }
}