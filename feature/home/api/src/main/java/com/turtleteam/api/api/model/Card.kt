package com.turtleteam.api.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Card (
    val numCards: String,
    val dateClose: String,
    val code: String,
    val limitBegin: String,
    val limitEnd: String,
    val bill: Bill
)

@Serializable
data class Bill(
    val title: String,
    val owner: String,
    val number: String,
    val dateOpen: String,
    val agreement: String,
    val remainder: String
)

@Serializable
data class CardShort(
    val code: Int,
    val dateClose: String,
    val id: String,
    val number: String,
    val paymentPeriodBegin: String,
    val paymentPeriodEnd: String
)