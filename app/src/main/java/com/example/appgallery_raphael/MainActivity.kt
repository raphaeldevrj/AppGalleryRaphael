package com.example.appgallery_raphael

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.presentation.ui.details.DetailCatFragment
import com.example.appgallery_raphael.presentation.ui.home.GalleryHomeFragment
import com.example.appgallery_raphael.presentation.viewmodel.GalleryStateFlow
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: GalleryViewModel by viewModels { ViewModelFactory(Repository()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadObserver()
        viewModel.showGalleryHome()
    }

    private fun loadObserver() {
        viewModel.state.observe(this) { state ->
            when (state) {
                GalleryStateFlow.GalleryHomeFlow -> showFragment(GalleryHomeFragment.newInstance())
                else -> {
                    showFragment(DetailCatFragment.newInstance())
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}