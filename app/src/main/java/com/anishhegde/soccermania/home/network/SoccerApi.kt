package com.anishhegde.soccermania.home.network

import com.anishhegde.soccermania.home.model.TeamResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SoccerApi {

    @GET("/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
    fun getEPLTeams(): Single<TeamResponse>
}