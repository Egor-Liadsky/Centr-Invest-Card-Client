package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.Settings
import com.turtleteam.impl.SettingsImpl
import org.koin.dsl.module

val settingsModule = module(createdAtStart = true) {
    single<Settings> { SettingsImpl(get()) }
}