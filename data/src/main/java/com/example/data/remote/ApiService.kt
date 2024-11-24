package com.example.data.remote

import com.example.domain.model.PlantsResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //token will be added to interceptor with the retrofit client
    // to be used for all apis needs the token
    //    https://trefle.io/api/v1/plants?
    //    token=0xOJ3_M4a3-zyJBJZ0t1hEz4pW3xO6gowOVsPBsRyw0
    //    &
    //    page=2
    @GET("{distributionPath}plants")
    suspend fun getPlants(@Path(value = "distributionPath", encoded = true) distributionPath: String?, // Nullable path parameter
                          @Query("page") page: Int): Response<PlantsResponse>
}
