package com.example.feature_home.presentation.random_photo_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.base.BaseFragment
import com.example.feature_home.databinding.FragmentRandomPhotosBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RandomPhotoFragment : BaseFragment<FragmentRandomPhotosBinding>() {

    private val viewModel: RandomPhotoViewModel by viewModels()

    private var adapter: RandomPhotoAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observePhotos()
        observeUiEvents()

        handleSwipeToRefresh()
    }

    private fun handleSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onEvent(RandomPhotoEvent.ScreenSwipeRefresh)
        }
    }

    private fun setupAdapter() {
        adapter = RandomPhotoAdapter(
            onAdd = { photo -> viewModel.onEvent(RandomPhotoEvent.AddButtonClicked(photo)) }
        )
        binding.rvRandomPhotos.adapter = adapter
    }

    private fun observePhotos() {
        viewModel.randomPhotos.observe(viewLifecycleOwner) { photos ->
            adapter?.submitList(photos)
        }
    }

    private fun observeUiEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.randomPhotosEvent.collect { event ->
                when (event) {
                    is RandomPhotoViewModel.UiRandomPhotoEvent.ShowSnackbar -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                    }
                    is RandomPhotoViewModel.UiRandomPhotoEvent.ShowProgressBar -> {
                        binding.pbLoadingItems.isVisible = event.isLoading
                    }
                    is RandomPhotoViewModel.UiRandomPhotoEvent.RefreshScreen -> {
                        binding.swipeRefreshLayout.isRefreshing = event.isRefreshing
                    }
                }
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRandomPhotosBinding.inflate(inflater, container, false)
}
