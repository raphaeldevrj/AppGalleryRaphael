package com.example.appgallery_raphael.presentation.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import com.example.appgallery_raphael.R
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.getTextChipChecked
import com.example.appgallery_raphael.presentation.viewmodel.GalleryViewModel
import com.example.appgallery_raphael.presentation.viewmodel.ViewModelFactory
import com.example.appgallery_raphael.setChipChecked
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.filter_gallery_list_fragment.*

class FilterGalleryListFragment : BottomSheetDialogFragment() {

    private val viewModel: GalleryViewModel by activityViewModels { ViewModelFactory(Repository()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_gallery_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filterValue.observe(viewLifecycleOwner, {
            chipgroup_type_img.setChipChecked(it[0])

        })

        btn_make_filter.setOnClickListener {
            if (chipgroup_type_img.getTextChipChecked().isNotEmpty()) {
                viewModel.getGalleryImgs(chipgroup_type_img.getTextChipChecked(), 1)
            } else {
                viewModel.getGalleryImgs(chipgroup_type_img.getTextChipChecked(), 1)
            }
        }

        viewModel.filterValue.value = arrayOf(chipgroup_type_img.checkedChipId)
        backPressed()
    }

    private fun backPressed() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            this.remove()
            activity?.onBackPressed()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    companion object {
        fun newInstanceFilter() = FilterGalleryListFragment()
    }
}