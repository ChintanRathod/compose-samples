package com.example.compose.jetsurvey.data

import com.example.compose.jetsurvey.Screen
import io.uniflow.core.flow.data.UIEvent


data class NavigationEvent(val screen : Screen) : UIEvent()