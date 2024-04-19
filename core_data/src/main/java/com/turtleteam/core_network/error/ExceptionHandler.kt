package com.turtleteam.core_network.error

import kotlinx.coroutines.CancellationException

suspend fun exceptionHandleable(
    executionBlock: suspend () -> Unit,
    failureBlock: (suspend (exception: Throwable) -> Unit)? = null,
    completionBlock: (suspend () -> Unit)? = null,
    conflictBlock: (suspend (exception: Throwable) -> Unit)? = null,
    unauthorizedBlock: (suspend (exception: Throwable) -> Unit)? = null
) {
    try {
        executionBlock()
    } catch (exception: Throwable) {
        when(exception.message) {
            Code.CONFLICT.name -> conflictBlock?.invoke(exception)
            Code.UNAUTHORIZED.name -> { unauthorizedBlock?.invoke(exception) }
            else -> { failureBlock?.invoke(exception) }
        }

        if (exception is CancellationException) throw exception
        println("Throwable caught, cause: ${exception.cause}, message: ${exception.message}")

    } finally {
        completionBlock?.invoke()
    }
}
