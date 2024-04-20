package com.turtleteam.eventapp

import android.app.Application
import com.turtleteam.eventapp.di.commonModule
import com.turtleteam.eventapp.di.dataModule.profileDataModule
import com.turtleteam.eventapp.di.featureModule.accountModule
import com.turtleteam.eventapp.di.featureModule.assistantModule
import com.turtleteam.eventapp.di.featureModule.detailCardModule
import com.turtleteam.eventapp.di.featureModule.homeModule
import com.turtleteam.eventapp.di.featureModule.optionModule
import com.turtleteam.eventapp.di.featureModule.profileModule
import com.turtleteam.eventapp.di.featureModule.settingsModule
import com.turtleteam.eventapp.di.featureModule.speakerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CentInvestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CentInvestApp)
            androidLogger(Level.DEBUG)
            modules(
                commonModule, detailCardModule, accountModule, homeModule, profileModule,
                settingsModule, assistantModule, speakerModule, optionModule, profileDataModule
            )
        }
    }
}
