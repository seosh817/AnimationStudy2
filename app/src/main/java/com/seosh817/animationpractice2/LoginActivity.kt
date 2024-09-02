package com.seosh817.animationpractice2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.*
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.seosh817.animationpractice2.databinding.LoginActivityBinding
import com.seosh817.animationpractice2.utils.viewBindings
import soup.animation.sample.utils.EventObserver

class LoginActivity: AppCompatActivity() {

    private val binding by viewBindings(LoginActivityBinding::inflate)
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.sharedElementEnterTransition = TransitionSet().apply {
            ordering = TransitionSet.ORDERING_TOGETHER
            addTransition(ChangeBounds().apply { pathMotion = ArcMotion() })
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
            addTransition(ChangeImageTransform())
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            viewModel.login()
        }
        binding.skipButton.setOnClickListener {
            finishAfterTransition()
        }
        viewModel.loginUiModel.observe(this, Observer {
            binding.loginButton.isEnabled = it.enabled
            binding.loginLabel.isGone = it.loading
            binding.loginProgress.isVisible = it.loading
        })
        viewModel.uiEvent.observe(this, EventObserver { success ->
            if (success) {
                binding.profileImage.setImageResource(R.drawable.user_icon)
                navigateToProfile(binding.profileImage)
                finish()
            } else {
                Toast.makeText(this, "Login Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun Activity.navigateToProfile(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, view, view.transitionName)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }
}