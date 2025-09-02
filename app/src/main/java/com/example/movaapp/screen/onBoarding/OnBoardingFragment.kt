package com.example.movaapp.screen.onBoarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.movaapp.adapter.PagerAdapter
import com.example.movaapp.databinding.FragmentOnBoardingBinding
import com.example.movaapp.model.PagerModel


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private val pagerAdapter=PagerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val texts = listOf(
            PagerModel(
                "Welcome to Mova",
                "The best movie streaming app of the century to make your days great!"
            ),
            PagerModel(
                "Explore Thousands of Movies",
                "From timeless classics to the latest blockbusters — all in one place."
            ),
            PagerModel(
                "Watch Anytime, Anywhere",
                "Stream seamlessly on all your devices — no ads, just pure entertainment."
            )
        )
        binding.vpText.adapter = pagerAdapter
        pagerAdapter.updateList(texts)


        binding.dotsIndicator.attachTo(binding.vpText)

        binding.vpText.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val lastPosition = pagerAdapter.itemCount - 1
                binding.buttonGetStarted.text =
                    if (position == lastPosition) "Get Started" else "Next"
            }
        })


        binding.buttonGetStarted.setOnClickListener {
            val currentItem = binding.vpText.currentItem
            val lastPosition = pagerAdapter.itemCount - 1
            if (currentItem < lastPosition) {
                binding.vpText.currentItem = currentItem + 1
            } else {
                onboarding()
//                findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
            }
        }
    }

    private fun onboarding(){
        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
        sp.edit().putBoolean("isCompleted", true).apply()
        }
}