package com.example.myapplication.presentation.ui.coffee_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.myapplication.presentation.components.FloatingAddButton
import com.example.myapplication.presentation.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CoffeeListScreen(viewModel: CoffeeListViewModel) {

    val isLoading = viewModel.isLoading.value
    val coffees = viewModel.coffees.value

    MyApplicationTheme {
        Scaffold(
            floatingActionButton =
            {
                FloatingAddButton(
                    onClickFun = viewModel::addCoffee
                )
            },
            content = {
                if (isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    if (coffees.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Click the + button to get a new coffee!")
                        }
                    } else {
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
                }
            }
        )
    }

}