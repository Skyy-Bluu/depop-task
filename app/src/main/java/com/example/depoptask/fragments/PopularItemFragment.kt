package com.example.depoptask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.depoptask.PopularItemCardView
import com.example.depoptask.R

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
        itemToDisplay?.let {
            cardView.initializeViewWithData(it)
        }
    }
}