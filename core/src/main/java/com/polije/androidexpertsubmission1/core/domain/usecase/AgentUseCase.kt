package com.polije.androidexpertsubmission1.core.domain.usecase

import com.polije.androidexpertsubmission1.core.data.Resource
import com.polije.androidexpertsubmission1.core.domain.model.Agent
import kotlinx.coroutines.flow.Flow

interface AgentUseCase {

    fun getAllAgent(): Flow<Resource<List<Agent>>>
    fun getFavoriteAgent(): Flow<List<Agent>>
    fun setFavoriteAgent(agent: Agent, state: Boolean)
}