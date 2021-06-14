package com.example.depoptask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.depoptask.PopularItemCardView
import com.example.depoptask.R
import com.example.depoptask.loadImageUsingGlide
import com.example.depoptask.network.ShopItem

class PopularListAdapter(private val onClickListener: OnClickListener) : ListAdapter<ShopItem,
        PopularListAdapter.ShopItemViewHolder>(DiffCallback) {
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(shopItem)
        }
        holder.bind(shopItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shopItemView = inflater.inflate(R.layout.popular_list_item, parent, false)
        return ShopItemViewHolder(shopItemView)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ShopItem>() {
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }
    }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cardView = view.findViewById<PopularItemCardView>(R.id.popularCard)
        private val userIdView = cardView.findViewById<TextView>(R.id.user_id)
        private val imageView = cardView.findViewById<ImageView>(R.id.item_img)
        private val description = cardView.findViewById<TextView>(R.id.item_description)
        private val viewPager = cardView.findViewById<ViewPager2>(R.id.pager)

        fun bind(shopItems: ShopItem) {
            useImageViewAndHideViewPager()
            description.text = shopItems.description
            userIdView.text = shopItems.userID.toString()
            itemView.post { shopItems.picturesData[0].loadImageUsingGlide(imageView) }
        }

        private fun useImageViewAndHideViewPager() {
            userIdView.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = imageView.id
            }
            viewPager.visibility = View.GONE
            imageView.visibility = View.VISIBLE
        }
    }

    class OnClickListener(val clickListener: (shopItem: ShopItem) -> Unit) {
        fun onClick(shopItems: ShopItem) = clickListener(shopItems)
    }
}