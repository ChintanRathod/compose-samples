/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.jetsurvey.signinsignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.jetsurvey.Screen.SignIn
import com.example.compose.jetsurvey.Screen.SignUp
import com.example.compose.jetsurvey.Screen.Survey
import com.example.compose.jetsurvey.data.NavigationEvent
import io.uniflow.android.AndroidDataFlow

class WelcomeViewModel(private val userRepository: UserRepository) : AndroidDataFlow() {

    fun handleContinue(email: String) = action {
        val event = if (userRepository.isKnownUserEmail(email)) {
            NavigationEvent(SignIn)
        } else {
            NavigationEvent(SignUp)
        }
        sendEvent(event)
    }

    fun signInAsGuest() = action {
        userRepository.signInAsGuest()
        sendEvent(NavigationEvent(Survey))
    }
}

@Suppress("UNCHECKED_CAST")
class WelcomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
