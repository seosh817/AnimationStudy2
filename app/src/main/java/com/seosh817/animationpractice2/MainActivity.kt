package com.seosh817.animationpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seosh817.animationpractice2.databinding.ActivityMainBinding
import com.seosh817.animationpractice2.utils.FragmentNavigator
import com.seosh817.animationpractice2.utils.viewBindings

class MainActivity : AppCompatActivity() {
    private val binding by viewBindings(ActivityMainBinding::inflate)

    private val navigator by lazy {
        FragmentNavigator(supportFragmentManager, R.id.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tab_home ->
                    navigator.navigate(
                        "home",
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                    ) { HomeFragment() }
                R.id.tab_settings ->
                    navigator.navigate(
                        "settings",
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                    ) { SettingsFragment() }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.tab_home
        }
        else {
            navigator.onRestoreInstanceState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navigator.onSaveInstanceState(outState)
    }
}