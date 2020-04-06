package com.anishhegde.soccermania.home.view

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anishhegde.soccermania.R
import com.anishhegde.soccermania.home.view.fragments.listTeam.ListTeamFragment
import com.anishhegde.soccermania.home.view.fragments.setWallpaper.PickWallPaperFragment
import com.anishhegde.soccermania.home.viewmodel.HomeViewModel
import com.anishhegde.soccermania.utils.inTransaction
import com.anishhegde.soccermania.utils.inTransactionWithBackStack


class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var homeViewModel: HomeViewModel
    lateinit var searchItem : MenuItem
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setCustomView(R.layout.action_bar)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        loadFragments()
        addBackStackPopListener()
        observeViewModel()
    }

    private fun addBackStackPopListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val currFrag = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if(currFrag is ListTeamFragment) {
                searchItem.isVisible = true
            }
        }
    }

    private fun observeViewModel() {
        homeViewModel.selectedTeam.observe(this, Observer {wallpapers ->
            wallpapers?.let {openWallpaper(it)}
        })
    }

    private fun loadFragments() {
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, ListTeamFragment.newInstance())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        searchItem = menu.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(this)
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.clearFocus()
        hideKeyboard()
        homeViewModel.search(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        homeViewModel.search(newText)
        return false
    }

    private fun openWallpaper(wallpaper: ArrayList<String>) {
        searchItem.isVisible = false
        supportFragmentManager.inTransactionWithBackStack {
            add(R.id.fragment_container, PickWallPaperFragment.newInstance(wallpaper))
        }

    }
}
