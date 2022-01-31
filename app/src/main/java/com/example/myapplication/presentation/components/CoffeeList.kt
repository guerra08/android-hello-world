package com.example.myapplication.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.models.CoffeeModel

@Composable
fun CoffeeList(coffees: List<CoffeeModel>){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(all = Dp(16F))
    ) {
        coffees.forEach { coffee ->
            Row(
                modifier = Modifier
                    .clickable {
                        Log.d("debug", coffee.uid)
                    }
                    .padding(8.dp)
            ) {
                Card(
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Rounded.Info, contentDescription = "Coffee")
                        Spacer(modifier = Modifier.size(4.dp))
                        Column {
                            Text(text = coffee.blendName, style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ))
                            Text(text = coffee.origin)
                        }
                    }
                }
            }
        }
    }
}