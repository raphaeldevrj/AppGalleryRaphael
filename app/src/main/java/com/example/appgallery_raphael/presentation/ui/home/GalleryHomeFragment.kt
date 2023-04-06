package com.example.appgallery_raphael.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appgallery_raphael.R
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.presentation.ui.CatsAdapter
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.component_btn_filter.*
import kotlinx.android.synthetic.main.home_galery_fragment.*

class GalleryHomeFragment : Fragment(R.layout.home_galery_fragment) {

    private val viewModel: GalleryViewModel by activityViewModels{ ViewModelFactory(Repository()) }
    var adapter = CatsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listCatsInGallery.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }

        recyclerViewCats.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewCats.adapter = adapter

        btnFilter.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_filterFragment)
        }

        getNameSearchView()

        viewModel.isFilter.observe(viewLifecycleOwner) {
            btnReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        btnReset.setOnClickListener {
            viewModel.getCats(1)
            viewModel.filterValue.value = arrayOf(0,0)
        }
    }

    private fun getNameSearchView(){
       searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getCatsByName(query.toString())
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
}