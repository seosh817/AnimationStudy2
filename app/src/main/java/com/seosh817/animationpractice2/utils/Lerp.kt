package com.seosh817.animationpractice2.utils

fun lerp(from: Int, to: Int, progress: Float): Float {
    return (1 - progress) * from + to * progress
}

fun lerp(from: Float, to: Float, progress: Float): Float {
    return (1 - progress) * from + to * progress
}

/** Linear interpolation between `startValue` and `endValue` by `fraction`.  */
fun lerp2(startValue: Float, endValue: Float, fraction: Float): Float {
    return startValue + fraction * (endValue - startValue)
}