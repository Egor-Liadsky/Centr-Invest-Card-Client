package com.whatrushka.impl.profile

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.whatrushka.api.profile.ProfileService
import com.whatrushka.api.profile.models.ProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ProfileServiceImpl(
    private val context: Context,
    serializer: Serializer<ProfileData>
) : ProfileService {
    private val Context.dataStore by dataStore("profile-data.json", serializer)

    private val profileDataFlow: Flow<ProfileData>
        get() = context.dataStore.data

    private suspend fun getProfileData() = profileDataFlow.first()

    override suspend fun saveUserProfile(data: ProfileData) {
        context.dataStore.updateData { data }
    }

    override suspend fun getUserProfile() = getProfileData()
}