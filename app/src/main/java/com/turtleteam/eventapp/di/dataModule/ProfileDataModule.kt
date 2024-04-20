package com.turtleteam.eventapp.di.dataModule

import androidx.datastore.core.Serializer
import com.whatrushka.api.profile.ProfileService
import com.whatrushka.api.profile.models.ProfileData
import com.whatrushka.impl.profile.ProfileServiceImpl
import com.whatrushka.impl.profile.models.ProfileDataSerializer
import org.koin.dsl.module

val profileDataModule = module {
    single<Serializer<ProfileData>> { ProfileDataSerializer() }

    single<ProfileService> { ProfileServiceImpl(get(), get()) }
}