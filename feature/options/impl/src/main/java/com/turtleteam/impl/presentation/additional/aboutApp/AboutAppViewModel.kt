package com.turtleteam.impl.presentation.additional.aboutApp

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.OptionNavigator

class AboutAppViewModel(
    private val navigator: OptionNavigator,
) : ViewModel() {

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
}