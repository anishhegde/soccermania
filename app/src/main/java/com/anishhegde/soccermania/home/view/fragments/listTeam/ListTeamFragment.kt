package com.anishhegde.soccermania.home.view.fragments.listTeam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.anishhegde.soccermania.R
import com.anishhegde.soccermania.home.model.Team
import com.anishhegde.soccermania.home.viewmodel.HomeViewModel
import com.anishhegde.soccermania.utils.HomeListClickListener
import kotlinx.android.synthetic.main.fragment_list_team.*


class ListTeamFragment : Fragment(), HomeListClickListener {

    lateinit var homeViewModel: HomeViewModel
    private lateinit var teamListAdapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            homeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
            teamListAdapter =
            TeamListAdapter(
                arrayListOf(),
                this
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_list_team, container, false)
        homeViewModel.load()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_teams)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = teamListAdapter
        }
        observeViewModel()
        return view
    }

    private fun observeViewModel() {
        homeViewModel.teams.observe(this, Observer {teams ->
            teams?.let{
                rv_teams.visibility = View.VISIBLE
                teamListAdapter.updateTeams(it)
            }
        })

        homeViewModel.loadingError.observe(this, Observer {isError ->
            isError?.let {tv_error.visibility = if(it) View.VISIBLE else View.GONE}
        })

        homeViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pb_main.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    tv_error.visibility = View.GONE
                    rv_teams.visibility = View.GONE
                }
            }
        })

        homeViewModel.searchString.observe(this, Observer {search ->
            search?.let {teamListAdapter.filter.filter(it)}
        })
    }

    override fun onItemSelected(team: Team) {
        homeViewModel.showWallpapers(team)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListTeamFragment()
                .apply {
                arguments = Bundle().apply {
                }
            }
    }

}
