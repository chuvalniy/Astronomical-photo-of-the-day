package com.example.astronomicalphotooftheday.presentation.apod_random_items_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.astronomicalphotooftheday.databinding.FragmentApodRandomBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
            launch {
                viewModel.isLoading.collectLatest { isLoading ->
                    if (isLoading) { // * Implement this in ViewModel *
                        binding.pbLoadingItems.visibility = View.VISIBLE
                    } else {
                        binding.pbLoadingItems.visibility = View.GONE
                    }
                }
            }

            launch {
                viewModel.listApods.collectLatest { items ->
                    items?.let {
                        adapter.submitList(it)
                    }
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRandomApods("5") // 5 is a test value
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
