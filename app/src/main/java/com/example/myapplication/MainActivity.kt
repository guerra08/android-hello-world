package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.ui.coffee_list.CoffeeListScreen
import com.example.myapplication.presentation.ui.coffee_list.CoffeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: CoffeeListViewModel = hiltViewModel()
            CoffeeListScreen(viewModel = viewModel)
        }
    }
}
