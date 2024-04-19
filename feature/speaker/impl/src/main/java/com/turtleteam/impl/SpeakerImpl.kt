package com.turtleteam.impl

import android.content.Context
import android.speech.tts.TextToSpeech
import com.turtleteam.api.Speaker
import java.util.Locale

interface SpeakerService: Speaker {
    fun init(context: Context)
    fun destroy()
}

class SpeakerImpl : SpeakerService {
    private val listener = TextToSpeech.OnInitListener { }
    private var speaker: TextToSpeech? = null
    override fun speak(string: String) {
        speaker?.speak(string, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun init(context: Context) {
        speaker = TextToSpeech(context, listener)
        speaker?.language = Locale("RU")
    }

    override fun destroy() {
        speaker?.let {
            it.stop()
            it.shutdown()
        }
    }

}
