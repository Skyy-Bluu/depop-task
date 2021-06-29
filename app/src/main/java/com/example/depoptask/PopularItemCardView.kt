package com.example.depoptask

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2
import com.example.depoptask.adapters.ViewPagerImageAdapter
import com.example.depoptask.network.ShopItem
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class PopularItemCardView(context: Context, attributes: AttributeSet) :
    FrameLayout(context, attributes) {
    private val descriptionTextView by lazy {
        findViewById<TextView>(R.id.item_description)
    }

    private val userIDTextView by lazy {
        findViewById<TextView>(R.id.user_id)
    }

    private val viewPager by lazy {
        findViewById<ViewPager2>(R.id.pager)
    }

    private val springDotsIndicator by lazy {
        findViewById<WormDotsIndicator>(R.id.spring_dots_indicator)
    }
    private val adapter = ViewPagerImageAdapter()

    fun initializeViewWithData(itemToDisplay: ShopItem){
        adapter.submitList(itemToDisplay.picturesData.toMutableList())
        springDotsIndicator.setViewPager2(viewPager)
        descriptionTextView.text = itemToDisplay.description
        userIDTextView.text = itemToDisplay.userID.toString()
    }
    init {
        inflate(context, R.layout.popular_item_card_view, this)
        viewPager.adapter = adapter
    }
}