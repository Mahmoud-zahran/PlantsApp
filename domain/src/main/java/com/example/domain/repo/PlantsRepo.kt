package com.example.domain.repo


import com.example.domain.model.PlantsResponse
import com.example.domain.model.Result

interface PlantsRepo {
   suspend fun getPlantsFromRemote(pageNumber:Int):Result<PlantsResponse>

}