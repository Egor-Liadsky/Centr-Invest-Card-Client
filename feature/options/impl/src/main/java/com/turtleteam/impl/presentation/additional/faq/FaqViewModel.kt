package com.turtleteam.impl.presentation.additional.faq

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.OptionNavigator

class FaqViewModel(
    private val navigator: OptionNavigator,
) : ViewModel() {

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
}