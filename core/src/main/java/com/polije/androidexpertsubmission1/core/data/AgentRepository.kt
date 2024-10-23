package com.polije.androidexpertsubmission1.core.data

import com.polije.androidexpertsubmission1.core.data.source.local.LocalDataSource
import com.polije.androidexpertsubmission1.core.data.source.remote.RemoteDataSource
import com.polije.androidexpertsubmission1.core.data.source.remote.network.ApiResponse
import com.polije.androidexpertsubmission1.core.data.source.remote.response.DataItem
import com.polije.androidexpertsubmission1.core.domain.model.Agent
import com.polije.androidexpertsubmission1.core.domain.repository.IAgentRepository
import com.polije.androidexpertsubmission1.core.utils.AppExecutors
import com.polije.androidexpertsubmission1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IAgentRepository {

    override fun getAllAgent(): Flow<Resource<List<Agent>>> =
        object : NetworkBoundResource<List<Agent>, List<DataItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Agent>> {
                return localDataSource.getAllAgent().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> =
                remoteDataSource.getAllAgent()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val menuList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAgent(menuList)
            }

            override fun shouldFetch(data: List<Agent>?): Boolean =
                data.isNullOrEmpty()

        }.asFlow()


    override fun getFavoriteAgent(): Flow<List<Agent>> {
        return localDataSource.getFavoriteAgent().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAgent(agent: Agent, state: Boolean) {
        val menuEntity = DataMapper.mapDomainToEntity(agent)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAgent(menuEntity, state) }
    }
}