package com.turtleteam.impl.presentation.additional.feedback

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.OptionNavigator

class FeedbackViewModel(
    private val navigator: OptionNavigator,
) : ViewModel() {

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
}