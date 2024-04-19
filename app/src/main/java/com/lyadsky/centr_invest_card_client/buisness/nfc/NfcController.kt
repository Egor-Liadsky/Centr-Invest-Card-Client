package com.lyadsky.centr_invest_card_client.buisness.nfc

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.util.Log
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import kotlin.experimental.and

class NfcController {
    fun readFromIntent(intent: Intent): List<String> {
        val action = intent.action
        val data = mutableListOf<String>()
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action || NfcAdapter.ACTION_TECH_DISCOVERED == action || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
            val rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            val messages = mutableListOf<NdefMessage>()
            if (rawMessages != null) {
                for (i in rawMessages.indices) {
                    messages.add(i, rawMessages[i] as NdefMessage)
                }
                data.addAll(buildTagViews(messages.toTypedArray()))
            }
        }
        return data
    }

    private fun buildTagViews(msg: Array<NdefMessage>?): List<String> {
        if (msg.isNullOrEmpty()) return emptyList()
        val data = mutableListOf<String>()

        msg.forEach { message ->
            message.records.forEach { record ->
                val textEncoding: Charset =
                    if ((record.payload[0] and 128.toByte()).toInt() == 0) Charsets.UTF_8
                    else Charsets.UTF_16
                val languageCodeLength: Int =
                    (record.payload[0] and 51).toInt() // Get the Language Code, e.g. "en"
                try {
                    data.add(
                        String(
                            record.payload,
                            languageCodeLength + 1,
                            record.payload.size - languageCodeLength - 1,
                            textEncoding
                        )
                    )
                } catch (e: UnsupportedEncodingException) {
                    Log.e("UnsupportedEncoding", e.toString())
                }
            }
        }

        return data
    }
}
