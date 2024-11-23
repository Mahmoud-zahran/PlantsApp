package com.example.plantsapp.di

import com.example.domain.repo.PlantsRepo
import com.example.domain.usecase.GetPlansUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providePlantsUseCase(plantsRepo: PlantsRepo): GetPlansUseCase{
        return GetPlansUseCase(plantsRepo)
    }

}