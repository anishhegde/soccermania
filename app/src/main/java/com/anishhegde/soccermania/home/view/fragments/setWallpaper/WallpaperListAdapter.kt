package com.anishhegde.soccermania.home.view.fragments.setWallpaper

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anishhegde.soccermania.R
import com.anishhegde.soccermania.utils.WallpaperClickListener
import com.anishhegde.soccermania.utils.getProgressDrawable
import com.anishhegde.soccermania.utils.loadImage
import kotlinx.android.synthetic.main.item_wallpaper.view.*


class WallpaperListAdapter(var wallpapers: ArrayList<String>, var listener: WallpaperClickListener) :
    RecyclerView.Adapter<WallpaperListAdapter.WallpaperViewHolder>() {

    fun updateTeams(newWallpapers: List<String>) {
        wallpapers.clear()
        wallpapers.addAll(newWallpapers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WallpaperViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false),
            listener
        )

    override fun getItemCount() = wallpapers.size

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.bind(wallpapers[position], wallpapers)
    }

    class WallpaperViewHolder(itemView: View, var listener: WallpaperClickListener?) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val fanArt = itemView.iv_item_wallpaper
        private val setWallpaper = itemView.bt_set_wallpaper
        private lateinit var wallpapers: ArrayList<String>

        fun bind(wallpaper: String, wallpapers: ArrayList<String>) {
            fanArt.loadImage(wallpaper, getProgressDrawable(itemView.context))
            setWallpaper.setOnClickListener(this)
            //Monetization Strategy
            if(adapterPosition > 0) {
                setWallpaper.text = itemView.context.resources.getString(R.string.upgrade_to_premium)
                setWallpaper.isEnabled = false
            }
            this.wallpapers = wallpapers
        }

        override fun onClick(v: View?) {
            listener?.onWallpaperClicked(wallpapers[adapterPosition])
        }
    }
}