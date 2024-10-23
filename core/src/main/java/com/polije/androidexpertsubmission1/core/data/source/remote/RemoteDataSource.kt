package com.polije.androidexpertsubmission1.core.data.source.remote

import android.util.Log
import com.polije.androidexpertsubmission1.core.BuildConfig
import com.polije.androidexpertsubmission1.core.data.source.remote.network.ApiResponse
import com.polije.androidexpertsubmission1.core.data.source.remote.network.ApiService
import com.polije.androidexpertsubmission1.core.data.source.remote.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllAgent(): Flow<ApiResponse<List<DataItem>>> {

        return flow {
            try {
                val response = apiService.getAgent()
                val dataArray = response.data
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}