package com.example.feature_home.presentation.random_photo_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.feature_home.databinding.AdapterPhotoItemBinding
import com.example.feature_home.domain.model.Photo

class RandomPhotoAdapter(
    private val onAdd: (Photo) -> Unit
) : ListAdapter<Photo, RandomPhotoAdapter.RandomPhotoViewHolder>(RandomPhotoDiffCallback) {

    class RandomPhotoViewHolder(
        private val binding: AdapterPhotoItemBinding,
        private val onAdd: (Photo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.imgPhoto.load(photo.url)
            binding.tvDate.text = photo.date
            binding.tvTitle.text = photo.title
            binding.tvContent.text = photo.explanation
            binding.btnAddFavorites.setOnClickListener { onAdd(photo) }
            binding.btnDetail.setOnClickListener {
                if (binding.extendedLinearLayout.visibility == View.GONE)
                    binding.extendedLinearLayout.visibility = View.VISIBLE
                else
                    binding.extendedLinearLayout.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomPhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RandomPhotoViewHolder(
            AdapterPhotoItemBinding.inflate(layoutInflater, parent, false),
            onAdd
        )
    }

    override fun onBindViewHolder(holder: RandomPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object RandomPhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.date == newItem.date
        }
    }
}