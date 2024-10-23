package com.polije.androidexpertsubmission1.core.data.source.remote.network

import com.polije.androidexpertsubmission1.core.data.source.remote.response.AgentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("agents")
    suspend fun getAgent(): AgentResponse
}