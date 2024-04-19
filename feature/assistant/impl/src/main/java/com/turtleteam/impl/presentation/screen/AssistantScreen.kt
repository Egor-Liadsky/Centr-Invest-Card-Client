package com.turtleteam.impl.presentation.screen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R.*
import com.turtleteam.core_view.view.textfields.AssistantTextField
import com.turtleteam.impl.presentation.viewModel.AssistantViewModel
import com.turtleteam.impl.presentation.viewModel.Sender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AssistantScreen(
    modifier: Modifier,
    viewModel: AssistantViewModel
) {
    val state = viewModel.state.collectAsState()
    val text = remember { mutableStateOf("") }

    val stt = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            viewModel.onMessageSend(result?.get(0).toString())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            reverseLayout = true
        ) {
            items(state.value.chat.reversed()) {
                val isAssistant = it.sender == Sender.ASSISTANT
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            viewModel.onMessageClick(it.text)
                        }
                        .animateItemPlacement(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        if (isAssistant) Alignment.Start else Alignment.End
                    ),
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (isAssistant)
                        Image(
                            painter = painterResource(id = drawable.ic_turtle_head),
                            contentDescription = null
                        )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = "Тортила",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = if (isAssistant) Color(0xFF45483C) else Color.Transparent,
                            letterSpacing = 0.5.sp,
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (isAssistant)
                                        Color(0xFFDEE6C5)
                                    else
                                        Color(0xFFBCECE4),
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 20.dp, vertical = 8.dp),
                            text = it.text,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            letterSpacing = 0.25.sp,
                        )
                    }
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp, top = 10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssistantTextField(
                modifier = Modifier.weight(0.6f),
                value = text.value,
                onValueChange = { text.value = it },
                enabled = state.value.textFieldEnabled,
                keyboardActions = KeyboardActions(onSend = {
                    viewModel.onMessageSend(text.value)
                    text.value = ""
                }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                onSendClick = {
                    viewModel.onMessageSend(text.value)
                    text.value = ""
                },
                onMicClick = {
                    kotlin.runCatching { stt.launch(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)) }
                }
            )
        }
    }
}