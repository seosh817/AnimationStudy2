package com.seosh817.animationpractice2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.withSpringForceProperties
import androidx.fragment.app.Fragment
import com.seosh817.animationpractice2.databinding.SettingsFragmentBinding

class SettingsFragment: Fragment() {

    private lateinit var binding: SettingsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view).apply {

            profileSection.setOnClickListener {
                activity?.run {
                    if (GlobalData.isLogin) {
                        navigateToProfile(profileImage)
                    } else {
                        navigateToLogin(profileImage)
                    }
                }
            }

            val panel = buttonPanel.springAnimationOf(DynamicAnimation.TRANSLATION_X)
            val icon1 = icon01.springAnimationOf(DynamicAnimation.TRANSLATION_X)
            val icon2 = icon02.springAnimationOf(DynamicAnimation.TRANSLATION_X)
            val icon3 = icon03.springAnimationOf(DynamicAnimation.TRANSLATION_X)

            arrow.setOnClickListener {
                it.isSelected = it.isSelected.not()
                if (it.isSelected) {
                    panel.animateToFinalPosition(0f)
                    icon1.animateToFinalPosition(0f)
                    icon2.animateToFinalPosition(0f)
                    icon3.animateToFinalPosition(0f)
                } else {
                    panel.animateToFinalPosition(resources.getDimension(R.dimen.settings_button_panel_translation_x))
                    val transX = resources.getDimension(R.dimen.settings_button_icon_translation_x)
                    icon1.animateToFinalPosition(transX)
                    icon2.animateToFinalPosition(transX)
                    icon3.animateToFinalPosition(transX)
                }
            }
        }
    }

    private fun View.springAnimationOf(viewProperty: DynamicAnimation.ViewProperty): SpringAnimation {
        return SpringAnimation(this, viewProperty)
            .withSpringForceProperties {
                dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                stiffness = SpringForce.STIFFNESS_LOW
            }
    }

    private fun Activity.navigateToProfile(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, view, view.transitionName)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    private fun Activity.navigateToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, view, view.transitionName)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }


}