package com.example.depoptask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.depoptask.R
import com.example.depoptask.adapters.PopularListAdapter
import com.example.depoptask.viewmodels.PopularItemsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularItemsFragment : Fragment() {
    private val viewModel: PopularItemsViewModel by viewModel()
    private val recyclerView: RecyclerView? get() = view?.findViewById(R.id.items_list)
    private val adapter = PopularListAdapter(PopularListAdapter.OnClickListener {
        viewModel.chooseItem(it)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressSpinner = view.findViewById<ProgressBar>(R.id.progress_loader)

        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
        viewModel.shopItems.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
                progressSpinner.visibility = View.GONE
            }
        })
        viewModel.navigateWithSelectedItem.observe(viewLifecycleOwner, {
            if (it != null) {
                this.findNavController().navigate(
                    PopularItemsFragmentDirections.actionPopularItemsFragmentToItemDetails(
                        it
                    )
                )
                viewModel.itemChosen()
            }
        })
    }
}