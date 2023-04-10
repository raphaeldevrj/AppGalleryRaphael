package com.example.appgallery_raphael.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.model.Gallery
import com.example.appgallery_raphael.presentation.ui.GalleryAdapter
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.home_galery_fragment.*


class GalleryHomeFragment : Fragment(com.example.appgallery_raphael.R.layout.home_galery_fragment) {

    private val viewModel: GalleryViewModel by activityViewModels { ViewModelFactory(Repository()) }
    private lateinit var adapter: GalleryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GalleryAdapter(::itemCallback)


        viewModel.listInGallery.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }

        recyclerViewCats.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewCats.adapter = adapter

        btnFilter.setOnClickListener {
            viewModel.showFilterFragment()
        }

        getNameSearchView()
        backPressed()
    }

    private fun itemCallback(item: Gallery) {
        viewModel.setItem(item)
        viewModel.showGalleryDetail()
    }

    private fun getNameSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getGalleryImgs(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.searchImages("cats")
    }

    private fun backPressed() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            this.remove()
            activity?.finish()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    companion object {
        fun newInstance() = GalleryHomeFragment()
    }
}