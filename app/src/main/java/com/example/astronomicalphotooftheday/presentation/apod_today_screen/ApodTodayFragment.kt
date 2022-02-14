package com.example.astronomicalphotooftheday.presentation.apod_today_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.astronomicalphotooftheday.databinding.FragmentApodTodayBinding
import dagger.hilt.android.AndroidEntryPoint
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

        // * Implement state variable in ViewModel *
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.isLoading.collectLatest { isLoading ->
                    if (isLoading) {
                        binding.pbLoading.visibility = View.VISIBLE
                        binding.divApodItem.visibility = View.GONE
                    } else {
                        binding.pbLoading.visibility = View.GONE
                        binding.divApodItem.visibility = View.VISIBLE
                    }
                }
            }
            launch {
                viewModel.apodItem.collectLatest { item ->
                    item?.let {
                        binding.tvTitle.text = it.title
                        binding.tvContent.text = it.explanation
                        binding.imgApod.load(it.url)
                    }
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