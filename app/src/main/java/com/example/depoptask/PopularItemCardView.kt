package com.example.depoptask

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class PopularItemCardView(context: Context, attributes: AttributeSet) :
    FrameLayout(context, attributes) {

    init {
        inflate(context, R.layout.popular_item_card_view, this)
    }
}