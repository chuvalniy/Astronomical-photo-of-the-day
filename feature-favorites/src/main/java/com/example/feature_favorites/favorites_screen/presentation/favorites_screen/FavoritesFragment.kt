package com.example.feature_favorites.favorites_screen.presentation.favorites_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.base.BaseFragment
import com.example.feature_favorites.databinding.FragmentFavoritesBinding
import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    private val viewModel: FavoritesViewModel by viewModels()
    private var adapter: FavoritesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observePhotos()
        observeUiEvent()

        binding.rvFavorites.adapter = adapter
    }

    private fun observeUiEvent() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favoritesEvent.collect { event ->
                when (event) {
                    is FavoritesViewModel.UiFavoritesEvent.ShowUndoDeletePhotoMessage -> {
                        showUndoSnackbar(event.photo)
                    }
                }
            }
        }
    }

    private fun showUndoSnackbar(photo: Photo) {
        Snackbar.make(requireView(), "Photo deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                viewModel.onEvent(FavoritesEvent.DeletedPhotoRestored(photo))
            }
            .show()
    }

    private fun observePhotos() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.photos.collect { data ->
                adapter?.submitList(data)
            }
        }
    }

    private fun setupAdapter() {
        adapter = FavoritesAdapter(
            onDelete = { photo ->
                viewModel.onEvent(FavoritesEvent.DeleteButtonClicked(photo))
            }
        )
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoritesBinding.inflate(inflater, container, false)
}