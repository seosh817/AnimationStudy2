package com.seosh817.animationpractice2

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.seosh817.animationpractice2.databinding.ActivitySplashBinding
import com.seosh817.animationpractice2.utils.viewBindings

class SplashActivity: AppCompatActivity() {
    private val binding by viewBindings(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val avd = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_splash)
        avd?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                navigateToNext()
            }
        })
        binding.splashIcon.setImageDrawable(avd)
        avd?.start()
    }

    private fun navigateToNext() {
        if (GlobalData.showOnBoarding) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}