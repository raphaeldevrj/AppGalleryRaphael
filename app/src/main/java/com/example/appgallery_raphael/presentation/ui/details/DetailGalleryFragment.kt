package com.example.appgallery_raphael.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.appgallery_raphael.R
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_gallery_fragment.*

class DetailGalleryFragment : Fragment(R.layout.details_gallery_fragment) {
    private val viewModel: GalleryViewModel by activityViewModels { ViewModelFactory(Repository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadObserver()
        backPressed()
        backButton()
    }

    private fun backPressed() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            this.remove()
            activity?.onBackPressed()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun backButton() {
        this.arrowBack.setOnClickListener(){
           requireActivity().onBackPressed()
        }
    }

    private fun loadObserver() {
        viewModel.item.observe(viewLifecycleOwner) { item ->
            if (item.images?.isNotEmpty() == true) {
                item.images?.first()?.link?.let {
                    Picasso.get().load(it).into(img_cats)
                }
            }
            catName.text = item.title
            followersGallery.text = item.followers.toString()
            descriptionCats.text = item.description
        }
    }

    companion object {
        fun newInstance() = DetailGalleryFragment()
    }
}