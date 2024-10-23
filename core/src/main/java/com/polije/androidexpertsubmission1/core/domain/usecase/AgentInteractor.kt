package com.polije.androidexpertsubmission1.core.domain.usecase

import com.polije.androidexpertsubmission1.core.data.Resource
import com.polije.androidexpertsubmission1.core.domain.model.Agent
import com.polije.androidexpertsubmission1.core.domain.repository.IAgentRepository
import kotlinx.coroutines.flow.Flow

class AgentInteractor(private val menuRepository: IAgentRepository): AgentUseCase {

    override fun getAllAgent(): Flow<Resource<List<Agent>>> = menuRepository.getAllAgent()

    override fun getFavoriteAgent(): Flow<List<Agent>> = menuRepository.getFavoriteAgent()

    override fun setFavoriteAgent(agent: Agent, state: Boolean) = menuRepository.setFavoriteAgent(agent, state)
}