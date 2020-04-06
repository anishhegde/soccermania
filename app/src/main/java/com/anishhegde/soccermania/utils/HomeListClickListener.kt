package com.anishhegde.soccermania.utils

import android.view.View
import com.anishhegde.soccermania.home.model.Team

interface HomeListClickListener {
    fun onItemSelected(team: Team)
}