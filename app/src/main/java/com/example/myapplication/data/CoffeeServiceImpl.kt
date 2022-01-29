package com.example.myapplication.data

import com.example.myapplication.domain.interfaces.CoffeeRepository
import com.example.myapplication.domain.models.CoffeeModel
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(): CoffeeRepository {

    private val BASEURL = "https://random-data-api.com/api/coffee/random_coffee"

    private val client: HttpClient = HttpClient(){
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json)
        }
    }

    override suspend fun getCoffee(): CoffeeModel {
        return client.get(BASEURL)
    }
}