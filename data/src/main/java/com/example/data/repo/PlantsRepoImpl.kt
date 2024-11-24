package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.model.PlantsResponse
import com.example.domain.model.Result
import com.example.domain.repo.PlantsRepo

class PlantsRepoImpl(private val apiService: ApiService):PlantsRepo {
    override suspend fun getPlantsFromRemote(distributionPath: String?, // Nullable path parameter
         pageNumber:Int): Result<PlantsResponse> {
        return try {
            val response = apiService.getPlants(distributionPath,pageNumber)
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error("Exception: ${e.localizedMessage}", e)
        }
    }
    }