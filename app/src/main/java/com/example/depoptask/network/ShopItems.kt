package com.example.depoptask.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShopItems(val objects: List<ShopItem>) : Parcelable

@Parcelize
data class ShopItem(
    val id: Int,
    @Json(name = "user_id") val userID: Int,
    val description: String,
    @Json(name = "pictures_data") val picturesData: List<PicturesData>
) : Parcelable

@Parcelize
data class PicturesData(val id: Int, val formats: Formats) : Parcelable

@Parcelize
data class Formats(
    val P1: Format,
    val P2: Format,
    val P4: Format,
    val P5: Format,
    val P6: Format,
    val P7: Format,
    val P8: Format,
) : Parcelable

@Parcelize
data class Format(val url: String, val height: Int, val width: Int) : Parcelable