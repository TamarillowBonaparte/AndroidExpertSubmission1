package com.polije.androidexpertsubmission1.core.data.source.local

import com.polije.androidexpertsubmission1.core.data.source.local.entity.AgentEntity
import com.polije.androidexpertsubmission1.core.data.source.local.room.AgentDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val agentDao: AgentDao){

    fun getAllAgent(): Flow<List<AgentEntity>> = agentDao.getAllAgent()

    fun getFavoriteAgent(): Flow<List<AgentEntity>> =
        agentDao.getFavoriteAgent()

    suspend fun insertAgent(menuList: List<AgentEntity>) =
        agentDao.insertAgent(menuList)

    fun setFavoriteAgent(agent: AgentEntity, newState: Boolean) {
        agent.isFavorite = newState
        agentDao.updateFavoriteAgent(agent)
    }
}