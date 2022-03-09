package com.example.taxi.presentation.trip.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taxi.databinding.ItemDateBinding

class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(date: String) {
        binding.dateTv.text = date
    }

    companion object {
        fun create(parent: ViewGroup): DateViewHolder {
            val binding = ItemDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DateViewHolder(binding)
        }
    }
}