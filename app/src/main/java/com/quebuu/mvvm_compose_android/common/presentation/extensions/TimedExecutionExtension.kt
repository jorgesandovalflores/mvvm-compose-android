package com.quebuu.mvvm_compose_android.common.presentation.extensions

import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

suspend inline fun runWithMinimumTime(time: Long = 2000, block: () -> Unit) {
    val measuredTime = measureTimeMillis(block)

    if (measuredTime < time) {
        delay(time - measuredTime)
    }
}

suspend inline fun <R> runCatchingWithMinimumTime(time: Long = 2000, block: () -> R): Result<R> {
    val result: Result<R>

    val measuredTime = measureTimeMillis {
        result = try {
            Result.success(block())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    if (measuredTime < time) {
        delay(time - measuredTime)
    }

    return result
}
