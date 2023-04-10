package com.example.appgallery_raphael

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.presentation.ui.details.DetailGalleryFragment
import com.example.appgallery_raphael.presentation.ui.filter.FilterGalleryListFragment
import com.example.appgallery_raphael.presentation.ui.home.GalleryHomeFragment
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: GalleryViewModel by viewModels { ViewModelFactory(Repository()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadObserver()
        viewModel.showGalleryHome()
        hideToolBar()
    }

    private fun hideToolBar() {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        getSupportActionBar()?.hide()
    }

    private fun loadObserver() {
        viewModel.state.observe(this) { state ->
            when (state) {
                GalleryViewModel.GalleryStateFlow.GalleryHomeFlow -> showFragment(GalleryHomeFragment.newInstance())
                GalleryViewModel.GalleryStateFlow.GalleryFilterFlow -> showFragment(FilterGalleryListFragment.newInstanceFilter())
                else -> {
                    showFragment(DetailGalleryFragment.newInstance())
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