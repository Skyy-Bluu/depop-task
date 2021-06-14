package com.example.depoptask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.depoptask.PopularItemCardView
import com.example.depoptask.R
import com.example.depoptask.adapters.ViewPagerImageAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class PopularItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemToDisplay = arguments?.let { PopularItemFragmentArgs.fromBundle(it).selectedItem }
        val cardView = view.findViewById<PopularItemCardView>(R.id.popularCard)
        val desc = cardView.findViewById<TextView>(R.id.item_description)
        val userID = cardView.findViewById<TextView>(R.id.user_id)
        val viewPager = cardView.findViewById<ViewPager2>(R.id.pager)
        val springDotsIndicator = cardView.findViewById<WormDotsIndicator>(R.id.spring_dots_indicator)
        val adapter = ViewPagerImageAdapter()

        adapter.submitList(itemToDisplay?.picturesData?.toMutableList())
        viewPager.adapter = adapter
        springDotsIndicator.setViewPager2(viewPager)
        desc.text = itemToDisplay?.description
        userID.text = itemToDisplay?.userID.toString()
    }
}