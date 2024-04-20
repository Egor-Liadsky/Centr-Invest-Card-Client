package com.turtleteam.impl.presentation.option.screen.components

import androidx.compose.runtime.Composable
import com.turtleteam.core_view.view.groupbuttons.ProfileButton
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.option.viewModel.OptionsViewModel

@Composable
fun getButtonsList(viewModel: OptionsViewModel): List<List<ProfileButton>> {
    val state = viewModel//todo
    return listOf(
        listOf(
            ProfileButton(
                icon = R.drawable.ic_newspaper,
                leadingText = "Редактировать профиль",
                trailingText = "",
                {}
            ),
            ProfileButton(
                icon = R.drawable.ic_notifications,
                leadingText = "Уведомления",
                trailingText = "Включены",
                {}
            ),
            ProfileButton(
                icon = R.drawable.ic_language,
                leadingText = "Язык",
                trailingText = "Русский",
                {}
            ),
        ),
        listOf(
            ProfileButton(
                icon = R.drawable.ic_account_logs,
                leadingText = "Данные о входах в аккаунт",
                trailingText = "",
                {}
            ),
            ProfileButton(
                icon = R.drawable.ic_health,
                leadingText = "Тема",
                trailingText = "Светлая",
                {}
            ),
            ProfileButton(
                icon = R.drawable.ic_parents,
                leadingText = "Данные родителей",
                trailingText = "",
                {}
            ),
        ),
        listOf(
            ProfileButton(
                icon = R.drawable.ic_app_version,
                leadingText = "Связаться с нами",
                trailingText = "",
                {}
            ),
            ProfileButton(
                icon = R.drawable.ic_lock,
                leadingText = "Политика конфиденциальности",
                trailingText = "",
                {}
            ),
            ProfileButton(
                icon = null,
                leadingText = "Версия приложения",
                trailingText = "",
                {}
            ),
        )
    )
}