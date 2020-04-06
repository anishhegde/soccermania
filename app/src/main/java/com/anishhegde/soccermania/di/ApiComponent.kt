package com.anishhegde.soccermania.di

import com.anishhegde.soccermania.home.viewmodel.HomeViewModel
import com.anishhegde.soccermania.home.network.SoccerService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: SoccerService)

    fun inject(viewModel: HomeViewModel)
}