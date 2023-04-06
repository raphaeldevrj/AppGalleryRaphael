package com.example.appgallery_raphael.network

import com.example.appgallery_raphael.model.CatsList
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    @GET("gallery/search")
    suspend fun getCats(@Query("page") page : Int): CatsList

    @GET("gallery/search")
    suspend fun getCatsByName(@Query("name") name : String): CatsList

    @GET("3/gallery")
    suspend fun getCatsFollowers(
        @Query("followers") follower : Int,
        @Query("page") page: Int
        ): CatsList

    @GET("gallery/search")
    suspend fun getCatsByDescription(@Query("description") description : String): CatsList

    @GET("gallery/search")
    suspend fun getCatsByImg(
        @Query("type") type : String,
        @Query("page") page: Int,
    ): CatsList


    @GET("gallery/search")
    suspend fun searchImages(@Query("q") searchBy: String): CatsList


object ApiKeys{

    const val CLIENT_ID = "Client-ID ba083072975c41c"
}

}