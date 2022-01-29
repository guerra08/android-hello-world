package com.example.myapplication.presentation.ui.coffee_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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

    private val _coffees: MutableState<List<CoffeeModel>> = mutableStateOf(ArrayList())
    private val _isLoading = mutableStateOf(false)

    val coffees: State<List<CoffeeModel>> = _coffees
    val isLoading: State<Boolean> = _isLoading

    fun addCoffee(){
        _isLoading.value = true
        viewModelScope.launch {
            val coffee = service.getCoffee()
            val currentCoffees = ArrayList(_coffees.value)
            currentCoffees.add(coffee)
            _coffees.value = currentCoffees
            _isLoading.value = false
        }
    }
}