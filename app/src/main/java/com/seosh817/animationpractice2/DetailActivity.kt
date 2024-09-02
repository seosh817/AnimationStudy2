package com.seosh817.animationpractice2

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.seosh817.animationpractice2.databinding.ActivityDetailBinding
import com.seosh817.animationpractice2.utils.viewBindings

class DetailActivity: AppCompatActivity() {
    private val binding by viewBindings(ActivityDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        window.enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, true).apply {
            addTarget(R.id.root)
        }
        window.allowEnterTransitionOverlap = true
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}