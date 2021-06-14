package com.example.depoptask

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.depoptask.network.PicturesData

fun PicturesData.loadImageUsingGlide(imageView: ImageView) {
    val imgUri = formats.P5.url
        .toUri().buildUpon().scheme("https").build()

    Glide.with(imageView.context)
        .load(imgUri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(imageView)
}