package com.example.astronomicalphotooftheday.presentation.apod_main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.astronomicalphotooftheday.R
import com.example.astronomicalphotooftheday.databinding.FragmentApodMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApodMainScreenFragment : Fragment() {

    private var _binding: FragmentApodMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodMainScreenBinding.inflate(inflater, container, false)

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}