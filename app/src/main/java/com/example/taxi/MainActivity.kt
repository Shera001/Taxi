package com.example.taxi

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.taxi.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.appBarMain.navHostFragmentContentMain.id) as (NavHostFragment)
        navController = navHostFragment.navController

        navView.setupWithNavController(navController)

        drawerLayout.setScrimColor(resources.getColor(R.color.color_transparent))
        drawerLayout.addDrawerListener(listener)

        setupDrawerContent()
    }

    private val listener = object : DrawerLayout.SimpleDrawerListener() {
        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            super.onDrawerSlide(drawerView, slideOffset)

            val difScaleOffset = slideOffset * 0.3f
            val offsetScale = 1 - difScaleOffset

            binding.appBarMain.coordinatorLayout.scaleX = offsetScale
            binding.appBarMain.coordinatorLayout.scaleY = 1 - slideOffset * 0.15f

            val xOffset = drawerView.width * slideOffset
            val xOffsetDiff = binding.appBarMain.coordinatorLayout.width * difScaleOffset / 2
            val xTranslation = xOffset - xOffsetDiff
            binding.appBarMain.coordinatorLayout.translationX = xTranslation
        }
    }

    private fun setupDrawerContent() {
        binding.navView.setNavigationItemSelectedListener { item ->
            selectDrawerItem(item)
            true
        }
    }

    private fun selectDrawerItem(item: MenuItem) {
        if (item.itemId == R.id.nav_trip) {
            navController.navigate(R.id.action_nav_home_to_nav_trip)
            binding.drawerLayout.closeDrawers()
        }
    }

    override fun onBackPressed() {
        binding.apply {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(binding.drawerLayout) || super.onSupportNavigateUp()
    }

    fun navigationDrawer() {
        if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}