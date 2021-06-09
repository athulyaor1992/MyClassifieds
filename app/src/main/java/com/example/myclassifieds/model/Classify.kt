package com.example.myclassifieds.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Classify(

    val created_at: String,
    val price: String,
    val name: String,
    val uid: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>

): Parcelable
