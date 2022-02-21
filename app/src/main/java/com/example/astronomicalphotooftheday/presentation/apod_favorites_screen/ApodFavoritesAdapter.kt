package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astronomicalphotooftheday.R
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.databinding.AdapterFavoritesItemBinding


class ApodFavoritesAdapter(
    private var onDelete: (ApodEntity) -> Unit,
) : ListAdapter<ApodEntity, ApodFavoritesAdapter.ApodFavoritesViewHolder>(ApodFavoritesDiffCallback) {

    class ApodFavoritesViewHolder(
        private val binding: AdapterFavoritesItemBinding,
        private var onDelete: (ApodEntity) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(apod: ApodEntity) = binding.apply {
            tvFavoritesTitleItem.text = apod.title
            tvFavoritesDateItem.text = apod.date
            tvFavoritesContentItem.text = apod.explanation
            imgFavoritesApodItem.load(apod.url) {
                placeholder(R.drawable.no_image_available)
            }
            btnRemoveFavorites.setOnClickListener {
                onDelete(apod)
            }
            btnFavoritesDetail.setOnClickListener {
                if (extendedLinearLayout.visibility == View.GONE)
                    extendedLinearLayout.visibility = View.VISIBLE
                else
                    extendedLinearLayout.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApodFavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApodFavoritesViewHolder(
            AdapterFavoritesItemBinding.inflate(layoutInflater, parent, false),
            onDelete
        )
    }

    override fun onBindViewHolder(
        holder: ApodFavoritesViewHolder,
        position: Int
    ) {
        val apodItem = getItem(position)
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