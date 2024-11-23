package com.example.plantsapp.di

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.Date

class ApiKeyInterceptor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url


        // Add required query parameters like token as needed to be added
        val urlWithParams = originalUrl.newBuilder()
            .addQueryParameter("token", "0xOJ3_M4a3-zyJBJZ0t1hEz4pW3xO6gowOVsPBsRyw0") // Add token
            .build()

        // Build the new request
        val requestWithParams = originalRequest.newBuilder()
            .header("User-Agent", "PlantsApp")
            .url(urlWithParams)
            .build()

        return chain.proceed(requestWithParams)
    }


}
