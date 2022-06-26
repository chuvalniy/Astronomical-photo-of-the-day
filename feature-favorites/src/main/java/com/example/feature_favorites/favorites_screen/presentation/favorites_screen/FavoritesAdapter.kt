package com.example.feature_favorites.favorites_screen.presentation.favorites_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.feature_favorites.databinding.AdapterFavoritesItemBinding
import com.example.feature_favorites.favorites_screen.domain.model.Photo

class FavoritesAdapter(
    private val onDelete: (Photo) -> Unit
) : ListAdapter<Photo, FavoritesAdapter.FavoritesViewHolder>(FavoritesDiffUtil) {

    class FavoritesViewHolder(
        private val binding: AdapterFavoritesItemBinding,
        private val onDelete: (Photo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.ivPhoto.load(photo.url)
            binding.tvTitle.text = photo.title
            binding.tvContent.text = photo.explanation
            binding.btnRemoveFavorites.setOnClickListener { onDelete(photo) }
            binding.btnDetails.setOnClickListener {
                if (binding.extendedLinearLayout.visibility == View.GONE)
                    binding.extendedLinearLayout.visibility = View.VISIBLE
                else
                    binding.extendedLinearLayout.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(
            AdapterFavoritesItemBinding.inflate(layoutInflater, parent, false),
            onDelete
        )
    }


    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object FavoritesDiffUtil : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.date == newItem.date
        }
    }
}