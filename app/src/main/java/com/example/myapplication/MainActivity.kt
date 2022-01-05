package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.myapplication.network.model.CoffeeModel
import com.example.myapplication.network.service.CoffeeService
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var service: CoffeeService
    private val coffeesState: MutableState<List<CoffeeModel>> = mutableStateOf(ArrayList())
    private val isLoading = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(
                    floatingActionButton = { SendRequestButton() },
                    content = {
                        if(isLoading.value){
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ){
                                CircularProgressIndicator()
                            }
                        }
                        else{
                            if (coffeesState.value.isEmpty()){
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ){
                                    Text(text = "Click the + button to get a new coffee!")
                                }
                            }
                            else {
                                ListOfCoffees(coffees = coffeesState.value)
                            }
                        }
                    }
                )
            }
        }
    }

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

    @Composable
    fun SendRequestButton() {
        FloatingActionButton(
            onClick = {
                isLoading.value = true
                CoroutineScope(Dispatchers.IO).launch {
                    val coffee = service.getCoffee()
                    withContext(Dispatchers.Main) {
                        val currentCoffees = ArrayList(coffeesState.value)
                        currentCoffees.add(coffee)
                        coffeesState.value = currentCoffees
                        isLoading.value = false
                    }
                }
            },
            content = {
                Icon(Icons.Filled.Add,"")
            }
        )
    }
}
