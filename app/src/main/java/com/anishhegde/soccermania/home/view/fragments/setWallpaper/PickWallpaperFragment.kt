package com.anishhegde.soccermania.home.view.fragments.setWallpaper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.anishhegde.soccermania.R
import com.anishhegde.soccermania.home.model.Team
import com.anishhegde.soccermania.home.view.fragments.listTeam.TeamListAdapter
import com.anishhegde.soccermania.utils.WallpaperClickListener

private const val WALLPAPERS_ARG = "wallpapers"

class PickWallPaperFragment : Fragment(), WallpaperClickListener{
    private var wallpapers: ArrayList<String> = arrayListOf()
    private lateinit var wallpaperListAdapter: WallpaperListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wallpapers = it.getStringArrayList(WALLPAPERS_ARG) as ArrayList<String>
        }
        activity.let {
            wallpaperListAdapter = WallpaperListAdapter(
                wallpapers,
                this
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pick_wall_paper, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_wallpapers)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = wallpaperListAdapter
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(wallpapers: ArrayList<String>) =
            PickWallPaperFragment()
                .apply {
                arguments = Bundle().apply {
                    putStringArrayList(WALLPAPERS_ARG, wallpapers)
                }
            }
    }

    override fun onWallpaperClicked(url: String) {
        //Not implemented
        Toast.makeText(activity, "Wallpaper Set", Toast.LENGTH_SHORT).show()
    }
}
