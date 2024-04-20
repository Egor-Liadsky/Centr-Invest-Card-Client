package com.whatrushka.impl.profile.models

import androidx.datastore.core.Serializer
import com.whatrushka.api.profile.models.ProfileData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class ProfileDataSerializer : Serializer<ProfileData> {
    override val defaultValue: ProfileData
        get() = ProfileData()

    override suspend fun readFrom(input: InputStream): ProfileData =
        try {
            Json.decodeFromString(
                deserializer = ProfileData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            println(e.stackTrace)
            defaultValue
        }

    override suspend fun writeTo(t: ProfileData, output: OutputStream) =
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = ProfileData.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
}