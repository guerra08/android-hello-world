package com.example.myapplication.presentation.nav

sealed class Screen(
    val route: String
){
    object CoffeeList: Screen("coffeeList")
    object CoffeeDetails: Screen("coffeeDetails")
}
