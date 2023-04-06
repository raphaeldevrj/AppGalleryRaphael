package com.example.appgallery_raphael.model

data class Cats (
    var id : String,
    var title: String,
    var followers : Int,
    var description: String?,
    val tags: List<Tags>,
    var images: List<Images>? = arrayListOf()
)
