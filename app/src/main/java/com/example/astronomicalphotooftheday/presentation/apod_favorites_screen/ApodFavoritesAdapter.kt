package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.databinding.AdapterFavoritesItemBinding
import com.example.astronomicalphotooftheday.domain.model.Apod

class ApodFavoritesAdapter(
    private var onDelete: (ApodEntity) -> Unit,
) : ListAdapter<ApodEntity, ApodFavoritesAdapter.ApodFavoritesViewHolder>(ApodFavoritesDiffCallback) {

    class ApodFavoritesViewHolder(
        val binding: AdapterFavoritesItemBinding,
        private var onDelete: (ApodEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(apod: ApodEntity) {
            binding.apply {
                btnRemoveFavorites.setOnClickListener {
                    onDelete(apod)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodFavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApodFavoritesViewHolder(
            AdapterFavoritesItemBinding.inflate(layoutInflater, parent, false),
            onDelete
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
        holder.bind(apodItem)
    }


    companion object ApodFavoritesDiffCallback : DiffUtil.ItemCallback<ApodEntity>() {
        override fun areItemsTheSame(oldItem: ApodEntity, newItem: ApodEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ApodEntity, newItem: ApodEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }
}