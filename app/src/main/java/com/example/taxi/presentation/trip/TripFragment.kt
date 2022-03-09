package com.example.taxi.presentation.trip

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taxi.R
import com.example.taxi.databinding.FragmentTripBinding
import com.example.taxi.presentation.trip.adapters.ItemDecoration
import com.example.taxi.presentation.trip.adapters.TripRecyclerAdapter
import com.example.taxi.presentation.trip.listeners.TripOnClickListener
import com.example.taxi.presentation.trip.models.TripModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripFragment : Fragment(), TripOnClickListener {

    private var binding: FragmentTripBinding? = null

    private val viewModel: TripViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTripBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tripHistoryAdapter = TripRecyclerAdapter(this)

        binding?.tripHistoryRv?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(ItemDecoration())
            adapter = tripHistoryAdapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.tripHistory.collect { list ->
                tripHistoryAdapter.submitList(list)
                viewModel.setShimmerVisible(false)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.shimmerVisible.collect {isVisible ->
                binding?.shimmerLayout?.isVisible  = isVisible
                binding?.tripHistoryRv?.isVisible  = !isVisible
            }
        }

        setupListener()
    }

    private fun launch() {

    }

    private fun setupListener() {
        binding?.backView?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onClick(position: Int, item: TripModel) {
        binding?.root?.let { layout ->
            Navigation.findNavController(layout)
                .navigate(R.id.navigateTripFragmentToTripDetailFragment)
        }
    }
}