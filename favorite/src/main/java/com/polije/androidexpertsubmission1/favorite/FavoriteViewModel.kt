package com.polije.androidexpertsubmission1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.polije.androidexpertsubmission1.core.domain.usecase.AgentUseCase

class FavoriteViewModel(agentUseCase: AgentUseCase) : ViewModel() {

    val favoriteMenu = agentUseCase.getFavoriteAgent().asLiveData()
}