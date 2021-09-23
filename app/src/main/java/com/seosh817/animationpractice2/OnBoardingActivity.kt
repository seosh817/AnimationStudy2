package com.seosh817.animationpractice2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.seosh817.animationpractice2.databinding.ActivityOnboardingBinding
import com.seosh817.animationpractice2.utils.viewBindings

class OnBoardingActivity: AppCompatActivity() {
    private val binding by viewBindings(ActivityOnboardingBinding::inflate)

    private val callback = object: ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}