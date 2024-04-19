package com.lyadsky.centr_invest_card_client.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class Storage(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val PINCODE = stringPreferencesKey("pincode")
        private const val DATASTORE_NAME = "settings"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    private val token by lazy {
        context.dataStore.data.map {
            val value = it[TOKEN_KEY]
            if (value.isNullOrBlank()) null else value
        }
    }

    private val pincode by lazy {
        context.dataStore.data.map {
            val value = it[PINCODE]
            if (value.isNullOrBlank()) null else value
        }
    }

    suspend fun setToken(token: String?) {
        context.dataStore.edit { settings ->
            settings[TOKEN_KEY] = token ?: ""
        }
    }

    suspend fun getToken(): String? {
        return token.first()
    }

    suspend fun setPincode(pincode: String?) {
        context.dataStore.edit { settings ->
            settings[PINCODE] = pincode ?: ""
        }
    }

    suspend fun getPincode(): String? {
        return pincode.first()
    }
}