package com.nouman.gittreadingrepo.helper


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineTestRule @ExperimentalCoroutinesApi constructor(
    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    @ExperimentalCoroutinesApi
    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
        val testDispatchers = testAppDispatchers(Dispatchers.Main)
        AppDispatchers.setDefault(testDispatchers)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
        AppDispatchers.resetDefault()
        dispatcher.cleanupTestCoroutines()
    }
}
