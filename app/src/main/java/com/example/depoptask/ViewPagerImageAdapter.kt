package com.example.depoptask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ViewPagerImageAdapter : ListAdapter<PicturesData,
        ViewPagerImageAdapter.PicturesDataViewHolder>(DiffCallback) {
    override fun onBindViewHolder(holder: PicturesDataViewHolder, position: Int) {
        val pictureData = getItem(position)
        holder.bind(pictureData)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicturesDataViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.sliding_images, parent, false)
        return PicturesDataViewHolder(contactView)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PicturesData>() {
        override fun areItemsTheSame(oldItem: PicturesData, newItem: PicturesData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PicturesData, newItem: PicturesData): Boolean {
            return oldItem == newItem
        }
    }

    class PicturesDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.sliding_image)

        fun bind(picture: PicturesData) {
            val imgUri = picture.formats.P5.url
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
    }
}