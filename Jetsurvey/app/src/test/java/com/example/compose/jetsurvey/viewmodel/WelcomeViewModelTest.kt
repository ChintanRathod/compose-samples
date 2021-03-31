package com.example.compose.jetsurvey.viewmodel

import com.example.compose.jetsurvey.Screen
import com.example.compose.jetsurvey.data.NavigationEvent
import com.example.compose.jetsurvey.signinsignup.UserRepository
import com.example.compose.jetsurvey.signinsignup.WelcomeViewModel
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import org.junit.Before
import org.junit.Test

class WelcomeViewModelTest : ViewModelTest() {

    lateinit var viewModel: WelcomeViewModel
    lateinit var tester: TestViewObserver

    @Before
    fun setup() {
        viewModel = WelcomeViewModel(UserRepository)
        tester = viewModel.createTestObserver()
    }

    @Test
    fun signInAsGuest() {
        viewModel.signInAsGuest()

        tester.verifySequence(
            NavigationEvent(Screen.Survey)
        )
    }

    @Test
    fun `handleContinue - known address`() {
        viewModel.handleContinue("signin@example.com")

        tester.verifySequence(
            NavigationEvent(Screen.SignIn)
        )
    }

    @Test
    fun `handleContinue - unknown address`() {
        viewModel.handleContinue("signup@example.com")

        tester.verifySequence(
            NavigationEvent(Screen.SignUp)
        )
    }

}