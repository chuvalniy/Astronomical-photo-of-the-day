package com.example.astronomicalphotooftheday.presentation.apod_multiple_items_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astronomicalphotooftheday.databinding.AdapterApodItemBinding
import com.example.astronomicalphotooftheday.domain.model.Apod

class ApodRandomAdapter(
) : ListAdapter<Apod, ApodRandomAdapter.ApodItemsViewHolder>(DiffCallback) {

    class ApodItemsViewHolder(
        val binding: AdapterApodItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnDetail.setOnClickListener {
                // Extend card with detail info
                if (binding.extendedLinearLayout.visibility == View.GONE)
                    binding.extendedLinearLayout.visibility = View.VISIBLE
                else
                    binding.extendedLinearLayout.visibility = View.GONE
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodItemsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApodItemsViewHolder(
            AdapterApodItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ApodItemsViewHolder, position: Int) {
        val apodItem = getItem(position)
        holder.binding.tvTitleItem.text = apodItem.title
        holder.binding.imgApodItem.load(apodItem.url)
        holder.binding.tvContentItem.text = apodItem.explanation
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Apod>() {
        override fun areItemsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem.date == newItem.date
        }

    }
}