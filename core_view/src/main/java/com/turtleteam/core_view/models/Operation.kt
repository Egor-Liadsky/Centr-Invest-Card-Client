package com.turtleteam.core_view.models

data class Operation(
    val id: String,
    val sum: String,
    val date: String,
    val bankRecipient: String,
    val billRecipient: String,
    val recipientType: String,
    val phoneRecipient: String,
    val status: String,
    val operationType: String,
    val operationCategory: String,
    val numberReceipt: String,
    val commission: String,
    val comment: String
)
