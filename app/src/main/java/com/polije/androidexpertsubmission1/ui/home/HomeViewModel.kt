package com.polije.androidexpertsubmission1.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.polije.androidexpertsubmission1.core.domain.usecase.AgentUseCase

class HomeViewModel(agentUseCase: AgentUseCase) : ViewModel() {

    val menu = agentUseCase.getAllAgent().asLiveData()
}