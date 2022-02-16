package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.astronomicalphotooftheday.databinding.FragmentApodFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ApodFavoritesFragment : Fragment() {

    private var _binding: FragmentApodFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ApodFavoritesViewModel by viewModels()

    private lateinit var adapter: ApodFavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodFavoritesBinding.inflate(inflater, container, false)

        adapter = ApodFavoritesAdapter()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.savedApods.collectLatest { apods ->
                apods?.let {
                    adapter.submitList(apods)
                }
            }
        }

        binding.rvFavoriteApods.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}