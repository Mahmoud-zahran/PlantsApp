package com.example.domain.repo


import com.example.domain.model.PlantsResponse
import com.example.domain.model.Result

interface PlantsRepo {
   suspend fun getPlantsFromRemote(distributionPath: String?, // Nullable path parameter
 pageNumber:Int):Result<PlantsResponse>

}