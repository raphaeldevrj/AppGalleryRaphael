package com.example.appgallery_raphael.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.appgallery_raphael.R
import kotlinx.android.synthetic.main.details_cats_fragment.*

class DetailCatFragment : Fragment(R.layout.details_cats_fragment) {

    private val args: DetailCatFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cats = args.cats

        catName.text = cats.name
        followersCats.text = cats.followers.toString()
        descriptionCats.text = cats.description
    }

}