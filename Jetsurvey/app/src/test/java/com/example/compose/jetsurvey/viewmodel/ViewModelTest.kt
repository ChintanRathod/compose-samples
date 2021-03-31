package com.example.compose.jetsurvey.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.uniflow.core.logger.DebugMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import io.uniflow.test.rule.UniflowTestDispatchersRule
import org.junit.Rule

abstract class ViewModelTest {

    init {
        UniFlowLogger.init(DebugMessageLogger())
    }

    @get:Rule
    var coroutinesMainDispatcherRule = UniflowTestDispatchersRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()
}