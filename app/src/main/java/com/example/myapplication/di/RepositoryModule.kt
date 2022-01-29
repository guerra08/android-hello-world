package com.example.myapplication.di

import com.example.myapplication.data.CoffeeRepositoryImpl
import com.example.myapplication.domain.interfaces.CoffeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoffeeRepository(): CoffeeRepository {
        return CoffeeRepositoryImpl()
    }

}