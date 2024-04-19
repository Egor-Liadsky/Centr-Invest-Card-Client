package com.lyadsky.centr_invest_card_client

import android.app.Application
import com.lyadsky.centr_invest_card_client.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class CardClientApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CardClientApp)
            allowOverride(true)
        }
    }
}