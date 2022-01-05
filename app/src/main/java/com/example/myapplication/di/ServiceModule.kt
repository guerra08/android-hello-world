package com.example.myapplication.di

import com.example.myapplication.network.service.CoffeeService
import com.example.myapplication.network.service.CoffeeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCoffeeService(): CoffeeService {
        return CoffeeServiceImpl()
    }

}