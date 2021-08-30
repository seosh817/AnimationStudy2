package com.seosh817.animationpractice2

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
}

data class HomeItemUiModel(
    @DrawableRes
    val resId: Int,
    val isNew: Boolean
)