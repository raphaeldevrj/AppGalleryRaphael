package com.example.appgallery_raphael.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgallery_raphael.data.repository.Repository
import com.example.appgallery_raphael.model.Cats
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: Repository) : ViewModel() {

    var listCatsInGallery = MutableLiveData<List<Cats>>()
    var filterValue = MutableLiveData<Array<Int>>()
    var isFilter = MutableLiveData<Boolean>()

    init {
        filterValue.value = arrayOf(0, 0)
        isFilter.value = false
    }

    fun getCats(page: Int) {
        viewModelScope.launch {
            val cats = repository.getCats(page)
            listCatsInGallery.value = cats.data
            isFilter.value = true
        }
    }

    fun getCatsByName(name: String) {
        viewModelScope.launch {
            val cats = repository.getCatsByName(name)
            listCatsInGallery.value = cats.data
            isFilter.value = true
        }
    }
    fun getCatsByImg(type: String, page: Int) {
            viewModelScope.launch {
                val cats = repository.getCatsByTypeImg(type, page)
                listCatsInGallery.value = cats.data
                isFilter.value = true
            }
        }

    fun getFollowersCats(followers: Int, page: Int) {
        viewModelScope.launch {
            val cats = repository.getFollowersCats(followers,page)
            listCatsInGallery.value = cats.data
            isFilter.value = true
        }
    }

    fun searchImages(searchBy: String) {
        viewModelScope.launch {
            listCatsInGallery.value = repository.searchImages(searchBy).data
            isFilter.value = true
        }
    }
}