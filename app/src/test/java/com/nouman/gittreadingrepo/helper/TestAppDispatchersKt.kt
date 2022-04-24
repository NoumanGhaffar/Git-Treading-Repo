package com.nouman.gittreadingrepo.helper

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

fun AppDispatchers.Default.setDefault(dispatchers: AppDispatchers) {
    override = dispatchers
}

fun AppDispatchers.Default.resetDefault() {
    override = null
}

@ExperimentalCoroutinesApi
fun testAppDispatchers(
    main: CoroutineDispatcher = TestCoroutineDispatcher(),
    computation: CoroutineDispatcher = main,
    io: CoroutineDispatcher = main
): AppDispatchers = object : AppDispatchers {
    override val main = (main as? MainCoroutineDispatcher) ?: MainCoroutineDispatcherWrapper(main)
    override val computation = computation
    override val io = io
}

private class MainCoroutineDispatcherWrapper(
    private val delegate: CoroutineDispatcher
) : MainCoroutineDispatcher() {

    override val immediate: MainCoroutineDispatcher
        get() = (delegate as? MainCoroutineDispatcher)?.immediate ?: this

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        delegate.dispatch(context, block)
    }

    @ExperimentalCoroutinesApi
    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return delegate.isDispatchNeeded(context)
    }
}
