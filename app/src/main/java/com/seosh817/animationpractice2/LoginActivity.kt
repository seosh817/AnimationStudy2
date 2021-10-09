package com.seosh817.animationpractice2

import android.os.Bundle
import android.transition.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.seosh817.animationpractice2.databinding.LoginActivityBinding
import com.seosh817.animationpractice2.utils.viewBindings

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

        
    }
}