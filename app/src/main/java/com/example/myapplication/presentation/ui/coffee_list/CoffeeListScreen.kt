package com.example.myapplication.presentation.ui.coffee_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myapplication.presentation.components.CoffeeList
import com.example.myapplication.presentation.components.FloatingAddButton
import com.example.myapplication.presentation.components.SearchBar
import com.example.myapplication.presentation.nav.Screen
import com.example.myapplication.presentation.theme.MyApplicationTheme

@Composable
fun CoffeeListScreen(
    viewModel: CoffeeListViewModel,
    navController: NavController
) {

    val isLoading = viewModel.isLoading.value
    val coffees = viewModel.coffees.value
    val filterText = viewModel.filterText.value

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
                        Column {
                            SearchBar(
                                searchText = filterText,
                                onValueChanged = viewModel::onQueryChanged,
                                onSearch = viewModel::filterCoffees
                            )
                            CoffeeList(
                                coffees = coffees,
                                onClickNavigateToDetails = {
                                    navController.navigate(Screen.CoffeeDetails.route)
                                }
                            )
                        }
                    }
                }
            }
        )
    }

}