package com.example.feature_home.presentation.today_photo_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.core.base.BaseFragment
import com.example.feature_home.databinding.FragmentTodayPhotoBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodayPhotoFragment : BaseFragment<FragmentTodayPhotoBinding>() {


    private val viewModel: TodayPhotoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observePhoto()
        observeUiEvent()

    }

    private fun observePhoto() {
        viewModel.todayPhoto.observe(viewLifecycleOwner) { photo ->
            binding.tvTitle.text = photo.title
            binding.tvContent.text = photo.explanation
            binding.tvDate.text = photo.date
            binding.imgApod.load(photo.url)
        }
    }

    private fun observeUiEvent() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.todayPhotoEvent.collect { event ->
                when (event) {
                    is TodayPhotoViewModel.UiTodayPhotoEvent.ShowSnackbar -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                    }
                    is TodayPhotoViewModel.UiTodayPhotoEvent.ShowProgressBar -> {
                        binding.pbLoading.isVisible = event.isLoading
                    }
                }
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTodayPhotoBinding.inflate(inflater, container, false)
}