package com.example.domain.usecase

import com.example.domain.repo.PlantsRepo


class GetPlansUseCase(private val plantsRepo: PlantsRepo) {
suspend operator fun invoke(pageNumber:Int) = plantsRepo.getPlantsFromRemote(pageNumber)
}