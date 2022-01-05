package com.example.myapplication.network.service

import com.example.myapplication.network.model.CoffeeModel

interface CoffeeService {

    suspend fun getCoffee(): CoffeeModel

}