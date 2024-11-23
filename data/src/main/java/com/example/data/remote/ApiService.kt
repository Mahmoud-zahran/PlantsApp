package com.example.data.remote

import com.example.domain.model.PlantsResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface ApiService {
    //token will be added to interceptor with the retrofit client
    // to be used for all apis needs the token
    //    https://trefle.io/api/v1/plants?
    //    token=0xOJ3_M4a3-zyJBJZ0t1hEz4pW3xO6gowOVsPBsRyw0
    //    &
    //    page=2
    @GET("plants")
    suspend fun getPlants( @Query("page") page: Int): Response<PlantsResponse>
}
