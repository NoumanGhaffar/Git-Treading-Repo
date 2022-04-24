package com.nouman.gittreadingrepo.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlin.coroutines.ContinuationInterceptor

inline fun CoroutineScope.exceptionHandler(
    crossinline block: suspend (Throwable) -> Unit
) = CoroutineExceptionHandler { _, throwable ->
    val mainDispatcher = (coroutineContext[ContinuationInterceptor] as? MainCoroutineDispatcher ?: AppDispatchers.main)
    launch(mainDispatcher.immediate) { block(throwable) }
}
