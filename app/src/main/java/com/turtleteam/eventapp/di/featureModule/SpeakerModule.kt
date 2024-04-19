package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.Speaker
import com.turtleteam.impl.SpeakerImpl
import com.turtleteam.impl.SpeakerService
import org.koin.dsl.module

val speakerModule = module {
    single<SpeakerService> { SpeakerImpl() }
    single<Speaker> { get<SpeakerService>() }
}