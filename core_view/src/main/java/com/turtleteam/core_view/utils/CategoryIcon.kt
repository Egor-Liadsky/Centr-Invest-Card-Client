package com.turtleteam.core_view.utils

import com.turtleteam.core_view.R

fun Int.categoryIcon(): Int =
    when (this) {
        -2 -> R.drawable.ic_reffil
        -1 -> R.drawable.ic_not_category
        1 -> R.drawable.ic_transport
        2 -> R.drawable.ic_medical
        3 -> R.drawable.ic_teatr
        4 -> R.drawable.ic_rzd
        else -> R.drawable.ic_not_category
    }