package com.example.appgallery_raphael.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cats (
    var id : Int,
    var name: String,
    var followers : Int,
    var description: String,
    var image : String,
    var typeImg: String
): Parcelable
