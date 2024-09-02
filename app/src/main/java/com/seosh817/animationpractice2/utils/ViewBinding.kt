package com.seosh817.animationpractice2.utils

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> Activity.viewBindings(
    crossinline inflater: (LayoutInflater) -> VB
): Lazy<VB> {
    return lazy(LazyThreadSafetyMode.NONE) {
        inflater.invoke(layoutInflater)
    }
}
