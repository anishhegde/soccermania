package com.anishhegde.soccermania.home.network

import com.anishhegde.soccermania.di.DaggerApiComponent
import com.anishhegde.soccermania.home.model.TeamResponse
import io.reactivex.Single
import javax.inject.Inject

class SoccerService {

    @Inject
    lateinit var api: SoccerApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getEPLTeams(): Single<TeamResponse> {
        return api.getEPLTeams()
    }
}