package com.example.domain.usecase

import com.example.domain.repo.PlantsRepo


class GetPlansUseCase(private val plantsRepo: PlantsRepo) {
suspend operator fun invoke(distributionPath: String?, // Nullable path parameter
 pageNumber:Int) = plantsRepo.getPlantsFromRemote(distributionPath,pageNumber)
}