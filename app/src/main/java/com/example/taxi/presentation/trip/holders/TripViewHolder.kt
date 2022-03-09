package com.example.taxi.presentation.trip.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taxi.databinding.ItemTripBinding
import com.example.taxi.presentation.trip.listeners.TripOnClickListener
import com.example.taxi.presentation.trip.models.TripModel

class TripViewHolder(private val binding: ItemTripBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TripModel, onClickListener: TripOnClickListener) {
        binding.apply {
            startAddressTv.text = item.startAddress
            endAddressTv.text = item.endAddress
            timeTv.text = item.time
            tripPriceTv.text = item.time
            carImage.setImageResource(item.imageId)

            root.setOnClickListener {
                onClickListener.onClick(adapterPosition, item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): TripViewHolder {
            val binding = ItemTripBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TripViewHolder(binding)
        }
    }
}