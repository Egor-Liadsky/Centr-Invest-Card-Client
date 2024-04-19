package com.lyadsky.centr_invest_card_client

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lyadsky.centr_invest_card_client.buisness.nfc.NfcController
import com.lyadsky.centr_invest_card_client.ui.theme.CentrInvestCardClientTheme
import java.nio.charset.Charset
import kotlin.experimental.and

import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.lyadsky.centr_invest_card_client.components.root.RootComponentImpl
import com.lyadsky.centr_invest_card_client.ui.root.RootScreen


class MainActivity : ComponentActivity() {
    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        Log.d("m", "Nfc supported $nfcAdapter")
        Log.d("m", "Nfc enabled " + (nfcAdapter.isEnabled).toString())

        val rootComponent = RootComponentImpl(componentContext = defaultComponentContext())

        enableEdgeToEdge()

        setContent {
            RootScreen(component = rootComponent)
        }
    }

    override fun onResume() {
        super.onResume()
        val pendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE
        )
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
        nfcAdapter.disableForegroundDispatch(this)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Log.d("m", intent.action.toString())
        Log.d("m", NfcController().readFromIntent(intent).toString())

        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                Log.d("m", messages.toString())
                messages.forEach { message ->
                    message.records.forEach { record ->
//                        Log.d("m", record.payload.to)
                    }
                }
            }
    }
}
