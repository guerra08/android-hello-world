package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.network.service.CoffeeService
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var service: CoffeeService
    private val textState = mutableStateOf("")
    private val isLoading = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding()
                ){
                    Surface(color = MaterialTheme.colors.background) {
                        SendRequestButton(text = "Send")
                    }
                    if (isLoading.value) {
                        CircularProgressIndicator()
                    }
                    else {
                        TextColumn(text = textState.value)
                    }
                }
            }
        }
    }

    @Composable
    fun TextColumn(text: String){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(text = text)
        }
    }

    @Composable
    fun SendRequestButton(text: String){
        Button(onClick = {
            isLoading.value = true
            CoroutineScope(Dispatchers.IO).launch {
                val coffee = service.getCoffee()
                withContext(Dispatchers.Main){
                    textState.value += "${coffee.toPrettyString()}\n"
                    isLoading.value = false
                }
            }
        }){
            Text(text = text)
        }
    }
}
