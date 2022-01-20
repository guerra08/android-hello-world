package com.example.myapplication.presentation.ui.coffee_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.model.CoffeeModel
import com.example.myapplication.network.service.CoffeeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeListViewModel @Inject constructor(
    private val service: CoffeeService
) : ViewModel() {

    val coffees: MutableState<List<CoffeeModel>> = mutableStateOf(ArrayList())
    val isLoading = mutableStateOf(false)

    fun addCoffee(){
        isLoading.value = true
        viewModelScope.launch {
            val coffee = service.getCoffee()
            val currentCoffees = ArrayList(coffees.value)
            currentCoffees.add(coffee)
            coffees.value = currentCoffees
            isLoading.value = false
        }
    }
}