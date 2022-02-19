package com.example.astronomicalphotooftheday.presentation.apod_random_items_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.astronomicalphotooftheday.databinding.FragmentApodRandomBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class ApodRandomFragment : Fragment() {

    private var _binding: FragmentApodRandomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ApodRandomViewModel by viewModels()

    private lateinit var adapter: ApodRandomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodRandomBinding.inflate(inflater, container, false)

        adapter = ApodRandomAdapter(
            onAdd = { apod ->
                viewModel.insertApods(apod)
            }
        )

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is ApodRandomEvent.Failure -> {
                        binding.apply {
                            pbLoadingItems.isVisible = false
                            rvApodItems.isVisible = false
                            imgNoInternetRandomApod.isVisible = true
                        }
                        Toast.makeText(activity, event.errorText, Toast.LENGTH_SHORT).show()
                    }
                    is ApodRandomEvent.Success -> {
                        binding.apply {
                            pbLoadingItems.isVisible = false
                            rvApodItems.isVisible = true
                        }
                        adapter.submitList(event.apods)
                    }
                    is ApodRandomEvent.Loading -> {
                        binding.apply {
                            pbLoadingItems.isVisible = true
                            rvApodItems.isVisible = false
                        }

                    }
                    else -> Unit
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRandomApods()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        binding.rvApodItems.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
