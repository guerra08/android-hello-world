package com.example.myapplication.presentation.ui.coffee_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                                onSearch = viewModel::filterCoffees,
                                onClearInputPress = viewModel::clearQuery
                            )
                            CoffeeList(
                                coffees = coffees
                            )
                        }
                    }
                }
            }
        )
    }

}