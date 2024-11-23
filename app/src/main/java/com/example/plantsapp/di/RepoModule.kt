package com.example.plantsapp.di

import com.example.data.remote.ApiService
import com.example.data.repo.PlantsRepoImpl
import com.example.domain.repo.PlantsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): PlantsRepo{
        return PlantsRepoImpl(apiService)
    }
}