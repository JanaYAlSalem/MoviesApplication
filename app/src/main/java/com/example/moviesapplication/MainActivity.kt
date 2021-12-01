package com.example.moviesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviesapplication.network.MoviesApi
import com.example.moviesapplication.overview.OverviewViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    val model: OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.overviewFragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            // types menu
            R.id.popular -> {
                model.getItemMovies("popular")
                return true
            }
            R.id.top_rated -> {
                model.getItemMovies("top_rated")
                return true
            }
            R.id.now_playing -> {
                model.getItemMovies("now_playing")
                return true
            }

            R.id.upcoming -> {
                model.getItemMovies("upcoming")
                return true

            }

            // filters menu
            R.id.adventure -> {
                model.getGenersMovies(12)
                return true
            }

            R.id.animation -> {
                model.getGenersMovies(16)
                return true
            }

            R.id.crime -> {
                model.getGenersMovies(80)
                return true
            }

            R.id.drama -> {
                model.getGenersMovies(18)
                return true
            }
            else -> return super.onOptionsItemSelected(item)


        } // end when
    }

}

