package com.example.taxi.presentation.trip.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taxi.presentation.trip.holders.DateViewHolder
import com.example.taxi.presentation.trip.holders.TripViewHolder
import com.example.taxi.presentation.trip.listeners.TripOnClickListener
import com.example.taxi.presentation.trip.models.TripModel

class TripRecyclerAdapter(private val onClickListener: TripOnClickListener) :
    ListAdapter<TripModel, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TRIP_ITEM_VIEW_TYPE -> TripViewHolder.create(parent)
            DATE_VIEW_TYPE -> DateViewHolder.create(parent)
            else -> TripViewHolder.create(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TripViewHolder -> holder.bind(
                item = getItem(position),
                onClickListener = onClickListener
            )
            is DateViewHolder -> holder.bind(date = getItem(position).date)
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position % 4 == 0) {
            DATE_VIEW_TYPE
        } else {
            TRIP_ITEM_VIEW_TYPE
        }

    companion object {
        const val TRIP_ITEM_VIEW_TYPE = 1
        const val DATE_VIEW_TYPE = 2

        object DiffCallback : DiffUtil.ItemCallback<TripModel>() {
            override fun areItemsTheSame(oldItem: TripModel, newItem: TripModel): Boolean =
                true

            override fun areContentsTheSame(oldItem: TripModel, newItem: TripModel): Boolean =
                oldItem == newItem
        }
    }
}