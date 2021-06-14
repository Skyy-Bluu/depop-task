package com.example.depoptask

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.depoptask.network.Format
import com.example.depoptask.network.Formats
import com.example.depoptask.network.PicturesData

fun PicturesData.loadImageUsingGlide(imageView: ImageView) {
    val imgUri = formats.formatBasedViewWidth(imageView.width).url
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

fun Formats.formatBasedViewWidth(width: Int): Format{
    return when{
        width <= 150 -> P2
        width <= 210 -> P4
        width <= 320 -> P5
        width <= 480 -> P6
        width <= 640 -> P1
        width <= 960 -> P7
        width >= 960 -> P8
        else -> P5
    }
}