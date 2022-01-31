package com.example.myapplication.presentation.ui.coffee_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.interfaces.CoffeeRepository
import com.example.myapplication.domain.models.CoffeeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeListViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) : ViewModel() {

    private val _originalCoffees: MutableState<List<CoffeeModel>> = mutableStateOf(ArrayList())
    private val _coffees: MutableState<List<CoffeeModel>> = mutableStateOf(ArrayList())
    private val _filterText = mutableStateOf("")
    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> get() = _isLoading
    val filterText: State<String> get() = _filterText
    val coffees: State<List<CoffeeModel>> get() = _coffees

    fun addCoffee(){
        _isLoading.value = true
        viewModelScope.launch {
            val coffee = coffeeRepository.getCoffee()
            val currentCoffees = ArrayList(_originalCoffees.value)
            currentCoffees.add(coffee)

            _originalCoffees.value = currentCoffees
            _coffees.value = currentCoffees
            _filterText.value = ""

            _isLoading.value = false
        }
    }

    fun filterCoffees() {
        if(_filterText.value.isEmpty())
            _coffees.value = _originalCoffees.value
        else {
            _coffees.value = _originalCoffees.value.filter {
                it.blendName.lowercase().contains(_filterText.value.lowercase())
            }
        }
    }

    fun onQueryChanged(text: String) {
        _filterText.value = text
        filterCoffees()
    }

    fun clearQuery(){
        _filterText.value = ""
        filterCoffees()
    }
}