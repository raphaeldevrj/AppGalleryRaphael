package com.example.appgallery_raphael.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.model.Gallery
import kotlinx.coroutines.launch


class GalleryViewModel(private val repository: Repository) : ViewModel() {

    private var _state = MutableLiveData<GalleryStateFlow>()
    val state : LiveData<GalleryStateFlow> get() = _state

    private var _item = MutableLiveData<Gallery>()
    val item : LiveData<Gallery> get() = _item

    private var _listInGallery = MutableLiveData<List<Gallery>>()
    val listInGallery: LiveData<List<Gallery>> get() = _listInGallery

    var filterValue = MutableLiveData<Array<Int>>()
    var isFilter = MutableLiveData<Boolean>()

    init {
        filterValue.value = arrayOf(0, 0)
        isFilter.value = false
    }

    fun setItem(item: Gallery) {
        _item.value = item
    }
    fun showGalleryHome() {
        _state.value = GalleryStateFlow.GalleryHomeFlow
    }

    fun showGalleryDetail() {
        _state.value = GalleryStateFlow.GalleryDetailFlow
    }

    fun showFilterFragment() {
        _state.value = GalleryStateFlow.GalleryFilterFlow
    }

    fun getGallery(page: Int) {
        viewModelScope.launch {
            val cats = repository.getCats(page)
            _listInGallery.value = cats.data
            isFilter.value = true
        }
    }

    fun getGalleryImgs(name: String) {
        viewModelScope.launch {
            val cats = repository.getCatsByName(name)
            _listInGallery.value = cats.data
            isFilter.value = true
        }
    }
    fun getGalleryImgs(type: String, page: Int) {
            viewModelScope.launch {
                val cats = repository.getCatsByTypeImg(type, page)
                _listInGallery.value = cats.data
                isFilter.value = true
            }
        }

    fun searchImages(searchBy: String) {
        viewModelScope.launch {
            _listInGallery.value = repository.searchImages(searchBy).data
            isFilter.value = true
        }
    }

    sealed class GalleryStateFlow {
        object GalleryHomeFlow : GalleryStateFlow()
        object GalleryDetailFlow : GalleryStateFlow()
        object GalleryFilterFlow : GalleryStateFlow()
    }
}

