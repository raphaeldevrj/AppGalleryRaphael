package com.example.appgallery_raphael.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.model.Cats
import kotlinx.coroutines.launch

sealed class GalleryStateFlow {
    object GalleryHomeFlow : GalleryStateFlow()
    object GalleryDetailFlow : GalleryStateFlow()
}
class GalleryViewModel(private val repository: Repository) : ViewModel() {

    private var _state = MutableLiveData<GalleryStateFlow>()
    val state : LiveData<GalleryStateFlow> get() = _state

    private var _item = MutableLiveData<Cats>()
    val item : LiveData<Cats> get() = _item

    private var _listCatsInGallery = MutableLiveData<List<Cats>>()
    val listCatsInGallery: LiveData<List<Cats>> get() = _listCatsInGallery

    var filterValue = MutableLiveData<Array<Int>>()
    var isFilter = MutableLiveData<Boolean>()

    init {
        filterValue.value = arrayOf(0, 0)
        isFilter.value = false
    }

    fun setItem(item: Cats) {
        _item.value = item
    }
    fun showGalleryHome() {
        _state.value = GalleryStateFlow.GalleryHomeFlow
    }

    fun showGalleryDetail() {
        _state.value = GalleryStateFlow.GalleryDetailFlow
    }

    fun getCats(page: Int) {
        viewModelScope.launch {
            val cats = repository.getCats(page)
            _listCatsInGallery.value = cats.data
            isFilter.value = true
        }
    }

    fun getCatsByName(name: String) {
        viewModelScope.launch {
            val cats = repository.getCatsByName(name)
            _listCatsInGallery.value = cats.data
            isFilter.value = true
        }
    }
    fun getCatsByImg(type: String, page: Int) {
            viewModelScope.launch {
                val cats = repository.getCatsByTypeImg(type, page)
                _listCatsInGallery.value = cats.data
                isFilter.value = true
            }
        }

    fun getFollowersCats(followers: Int, page: Int) {
        viewModelScope.launch {
            val cats = repository.getFollowersCats(followers,page)
            _listCatsInGallery.value = cats.data
            isFilter.value = true
        }
    }

    fun searchImages(searchBy: String) {
        viewModelScope.launch {
            _listCatsInGallery.value = repository.searchImages(searchBy).data
            isFilter.value = true
        }
    }
}