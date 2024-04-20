package com.turtleteam.core_view.utils

import com.turtleteam.core_view.R

fun Int.categoryIcon(): Int =
    when (this) {
        -2 -> R.drawable.ic_reffil
        -1 -> R.drawable.ic_privileges
        else -> {
            R.drawable.ic_bank
        }
    }