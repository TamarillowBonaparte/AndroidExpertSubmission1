package com.polije.androidexpertsubmission1.di

import com.polije.androidexpertsubmission1.core.domain.usecase.AgentInteractor
import com.polije.androidexpertsubmission1.core.domain.usecase.AgentUseCase
import com.polije.androidexpertsubmission1.ui.detail.MenuDetailViewModel
import com.polije.androidexpertsubmission1.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AgentUseCase> { AgentInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MenuDetailViewModel(get()) }
}