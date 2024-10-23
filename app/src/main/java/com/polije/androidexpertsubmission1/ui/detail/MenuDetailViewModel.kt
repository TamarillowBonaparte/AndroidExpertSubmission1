package com.polije.androidexpertsubmission1.ui.detail

import androidx.lifecycle.ViewModel
import com.polije.androidexpertsubmission1.core.domain.model.Agent
import com.polije.androidexpertsubmission1.core.domain.usecase.AgentUseCase

class MenuDetailViewModel(private val agentUseCase: AgentUseCase): ViewModel() {

    fun setFavoriteMenu(agent: Agent, newStatus: Boolean) =
        agentUseCase.setFavoriteAgent(agent, newStatus)
}