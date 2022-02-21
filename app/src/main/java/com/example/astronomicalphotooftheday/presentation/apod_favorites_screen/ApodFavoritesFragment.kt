package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.astronomicalphotooftheday.core.base.BaseFragment
import com.example.astronomicalphotooftheday.databinding.FragmentApodFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ApodFavoritesFragment : BaseFragment<FragmentApodFavoritesBinding>() {

    private val viewModel: ApodFavoritesViewModel by viewModels()

    private lateinit var adapter: ApodFavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ApodFavoritesAdapter(
            onDelete = { apod ->
                viewModel.deleteApod(apod)
            }
        )

        viewModel.savedApods.observe(this.viewLifecycleOwner) { apods ->
            apods.let {
                adapter.submitList(it)
            }
        }

        binding.rvFavoriteApods.adapter = adapter

    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentApodFavoritesBinding.inflate(inflater, container, false)
}