package com.whatrushka.api.profile

import com.whatrushka.api.profile.models.ProfileData

interface ProfileService {
    suspend fun saveUserProfile(data: ProfileData)

    suspend fun getUserProfile(): ProfileData

}