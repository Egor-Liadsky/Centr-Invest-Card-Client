package com.turtleteam.impl.extension

import java.security.MessageDigest

fun String.hashString(algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(this.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}