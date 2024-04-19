package com.turtleteam.impl.presentation.payment.state

data class PaymentState(
    val requisitesIsHidden: Boolean = false,

    val phoneVariantText: String = "",

    val sumText: String = "",
    val commentText: String = "",

    val selectBank: String? = null,
    val selectCategory: String? = null,
)