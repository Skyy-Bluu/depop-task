package com.example.depoptask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.depoptask.R
import com.example.depoptask.loadImageUsingGlide
import com.example.depoptask.network.PicturesData

class ViewPagerImageAdapter : ListAdapter<PicturesData,
        ViewPagerImageAdapter.PicturesDataViewHolder>(DiffCallback) {
    override fun onBindViewHolder(holder: PicturesDataViewHolder, position: Int) {
        val pictureData = getItem(position)
        holder.itemView.post { holder.bind(pictureData) }
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
        fun bind(picturesData: PicturesData) {
            picturesData.loadImageUsingGlide(imageView)
        }
    }
}