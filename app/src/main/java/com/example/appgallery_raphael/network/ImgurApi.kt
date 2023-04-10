package com.example.appgallery_raphael.network

import com.example.appgallery_raphael.model.GalleryList
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search")
    suspend fun getCats(@Query("page") page: Int): GalleryList

    @GET("gallery/search")
    suspend fun getCatsByName(@Query("name") name: String): GalleryList

    @GET("gallery/search")
    suspend fun getCatsByImg(
        @Query("type") type: String,
        @Query("page") page: Int,
    ): GalleryList

    @GET("gallery/search")
    suspend fun searchImages(@Query("q") searchBy: String): GalleryList


}