package com.anishhegde.soccermania.home.view.fragments.listTeam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.anishhegde.soccermania.R
import com.anishhegde.soccermania.home.model.Team
import com.anishhegde.soccermania.utils.HomeListClickListener
import com.anishhegde.soccermania.utils.getProgressDrawable
import com.anishhegde.soccermania.utils.loadImage
import kotlinx.android.synthetic.main.item_team_home.view.*


class TeamListAdapter(var teams: ArrayList<Team>, var listener: HomeListClickListener?) :
    RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>(), Filterable {

    var filteredTeams = teams
    fun updateTeams(newTeams: List<Team>) {
        teams.clear()
        teams.addAll(newTeams)
        filteredTeams = teams
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team_home, parent, false),
            listener
        )

    override fun getItemCount() = filteredTeams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(filteredTeams[position], filteredTeams)
    }

    class TeamViewHolder(itemView: View, var listener: HomeListClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val teamName = itemView.tv_team_name
        private val stadium = itemView.tv_stadium
        private val location = itemView.tv_location
        private val teamImage = itemView.iv_item_team_home
        private val wallpaperButton = itemView.bt_wallpapers
        private lateinit var teams : ArrayList<Team>

        fun bind(team: Team, teamList: ArrayList<Team>) {
            teamName.text = team.strTeam
            stadium.text = team.strStadium
            location.text = team.strStadiumLocation
            teamImage.loadImage(team.strTeamBadge, getProgressDrawable(itemView.context))
            wallpaperButton.setOnClickListener(this)
            teams = teamList
        }

        override fun onClick(v: View?) {
            listener?.onItemSelected(teams[adapterPosition])
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchString = constraint.toString()
                filteredTeams = if (searchString.isEmpty()) {
                    teams
                } else {
                    var tempFilteredTeams : ArrayList<Team> = arrayListOf()
                    for (team in teams) {
                        team.strTeam?.let {
                            if (it.contains(searchString, ignoreCase = true)) {
                                tempFilteredTeams.add(team)
                            }
                        }
                    }
                    tempFilteredTeams
                }

                val filteredResults = FilterResults()
                filteredResults.values = filteredTeams
                return  filteredResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredTeams = results?.values as ArrayList<Team>
                notifyDataSetChanged()
            }
        }
    }
}