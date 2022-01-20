package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.nav.Screen
import com.example.myapplication.presentation.ui.coffee.CoffeeDetailsScreen
import com.example.myapplication.presentation.ui.coffee_list.CoffeeListScreen
import com.example.myapplication.presentation.ui.coffee_list.CoffeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.CoffeeList.route){
                composable(route = Screen.CoffeeList.route) {
                    val viewModel: CoffeeListViewModel = hiltViewModel()
                    CoffeeListScreen(viewModel = viewModel, navController = navController)
                }
                composable(route = Screen.CoffeeDetails.route) {
                    CoffeeDetailsScreen()
                }
            }
        }
    }
}
