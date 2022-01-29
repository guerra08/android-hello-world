package com.example.myapplication.domain.interfaces

import com.example.myapplication.domain.models.CoffeeModel

interface CoffeeRepository {

    suspend fun getCoffee(): CoffeeModel

}