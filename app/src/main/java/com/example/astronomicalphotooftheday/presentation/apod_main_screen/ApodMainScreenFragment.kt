package com.example.astronomicalphotooftheday.presentation.apod_main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.astronomicalphotooftheday.R
import com.example.astronomicalphotooftheday.core.base.BaseFragment
import com.example.astronomicalphotooftheday.databinding.FragmentApodMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApodMainScreenFragment : BaseFragment<FragmentApodMainScreenBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imgTodayApod.setImageResource(R.drawable.today_apod_img)
            imgRandomApod.setImageResource(R.drawable.random_apod_img)
            cvToday.setOnClickListener {
                findNavController().navigate(R.id.navigateToApodTodayFragment)
            }
            cvRandom.setOnClickListener {
                findNavController().navigate(R.id.navigateToApodItemsFragment)
            }
        }

    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentApodMainScreenBinding.inflate(inflater, container, false)
}