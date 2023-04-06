package com.example.appgallery_raphael.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.appgallery_raphael.R
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_cats_fragment.*

class DetailCatFragment : Fragment(R.layout.details_cats_fragment) {
    private val viewModel: GalleryViewModel by activityViewModels{ ViewModelFactory(Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadObserver()
    }

    private fun loadObserver() {
        viewModel.item.observe(viewLifecycleOwner) { item ->
            if (item.images?.isNotEmpty() == true) {
                item.images?.first()?.link?.let {
                    Picasso.get().load(it).into(img_cats)
                }
            }
            catName.text = item.title
            followersCats.text = item.followers.toString()
            descriptionCats.text = item.description
        }
    }
    companion object {
        fun newInstance() = DetailCatFragment()
    }
}