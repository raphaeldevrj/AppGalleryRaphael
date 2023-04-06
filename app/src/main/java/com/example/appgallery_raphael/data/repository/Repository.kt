package com.example.appgallery_raphael.data.repository

import com.example.appgallery_raphael.model.CatsList
import com.example.appgallery_raphael.network.RetrofitInstance

class Repository {

    suspend fun getCats(page: Int): CatsList {
        return RetrofitInstance.apiImgur.getCats(page)
    }

    suspend fun getCatsByTypeImg(type : String, page:Int): CatsList{
        return RetrofitInstance.apiImgur.getCatsByImg(type, page)
    }

    suspend fun getCatsByName(name: String): CatsList{
        return RetrofitInstance.apiImgur.getCatsByName(name)
    }

    suspend fun getFollowersCats(followers: Int,page: Int): CatsList{
        return RetrofitInstance.apiImgur.getCatsFollowers(followers,page)
    }


}