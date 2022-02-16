package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astronomicalphotooftheday.databinding.AdapterFavoritesItemBinding
import com.example.astronomicalphotooftheday.domain.model.Apod

class ApodFavoritesAdapter :
    ListAdapter<Apod, ApodFavoritesAdapter.ApodFavoritesViewHolder>(ApodFavoritesDiffCallback) {

    class ApodFavoritesViewHolder(
        val binding: AdapterFavoritesItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodFavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApodFavoritesViewHolder(
            AdapterFavoritesItemBinding.inflate(layoutInflater, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ApodFavoritesViewHolder, position: Int) {
        val apodItem = getItem(position)
        holder.binding.apply {
            tvFavoritesTitleItem.text = apodItem.title
            tvFavoritesDateItem.text = apodItem.date
            tvFavoritesContentItem.text = apodItem.explanation
            imgFavoritesApodItem.load(apodItem.url)
        }

    }


    companion object ApodFavoritesDiffCallback: DiffUtil.ItemCallback<Apod>() {
        override fun areItemsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem.date == newItem.date
        }

    }
}