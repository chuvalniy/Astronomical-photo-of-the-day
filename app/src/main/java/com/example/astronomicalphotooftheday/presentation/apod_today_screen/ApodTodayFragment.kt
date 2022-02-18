package com.example.astronomicalphotooftheday.presentation.apod_today_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.astronomicalphotooftheday.R
import com.example.astronomicalphotooftheday.databinding.FragmentApodTodayBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ApodTodayFragment : Fragment() {

    private var _binding: FragmentApodTodayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ApodTodayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodTodayBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is ApodTodayEvent.Success -> {
                        binding.pbLoading.isVisible = false
                        binding.svTodayApod.isVisible = true
                        binding.tvTitle.text = event.apod.title
                        binding.tvDate.text = event.apod.date
                        binding.tvContent.text = event.apod.explanation
                        binding.imgApod.load(event.apod.url)
                        binding.btnAddFavoritesFromToday.setOnClickListener {
                            viewModel.insertApods(event.apod)
                        }
                    }
                    is ApodTodayEvent.Failure -> {
                        binding.pbLoading.isVisible = false
                        binding.svTodayApod.isVisible = false
                        binding.imgNoInternetTodayApod.isVisible = true
                        Toast.makeText(activity, event.errorText, Toast.LENGTH_SHORT).show()
                    }
                    is ApodTodayEvent.Loading -> {
                        binding.pbLoading.isVisible = true
                        binding.svTodayApod.isVisible = false
                    }
                    else -> Unit
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}