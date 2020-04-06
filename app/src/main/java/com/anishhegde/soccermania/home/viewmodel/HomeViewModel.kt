package com.anishhegde.soccermania.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anishhegde.soccermania.di.DaggerApiComponent
import com.anishhegde.soccermania.home.network.SoccerService
import com.anishhegde.soccermania.home.model.Team
import com.anishhegde.soccermania.home.model.TeamResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : ViewModel(){

    @Inject
    lateinit var soccerService: SoccerService

    private val disposable = CompositeDisposable()
    val teams = MutableLiveData<List<Team>>()
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()
    val searchString = MutableLiveData<String?>()
    val selectedTeam = MutableLiveData<ArrayList<String>>()

    init {
        DaggerApiComponent.create().inject(this);
    }

    fun load() {
        fetchEPLTeams()
    }

    fun search(searchQuery: String?) {
        searchString.value = searchQuery
    }

    fun showWallpapers(team: Team) {
        var wallpapers = ArrayList<String>()
        team.strTeamFanart1?.let { wallpapers.add(it) }
        team.strTeamFanart2?.let { wallpapers.add(it) }
        team.strTeamFanart3?.let { wallpapers.add(it) }
        team.strTeamFanart4?.let { wallpapers.add(it) }

        selectedTeam.value = wallpapers
    }

    private fun fetchEPLTeams() {
        loading.value = true
        disposable.add(
            soccerService.getEPLTeams()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<TeamResponse>(){
                    override fun onSuccess(value: TeamResponse?) {
                        teams.value = value?.teams
                        loadingError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loadingError.value = true
                        loading.value = false
                    }

                })
        )
    }

}