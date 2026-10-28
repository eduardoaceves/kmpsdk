/*
 * NavigationController.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.controller

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.login.navigation.LoginNavigation

@Composable
fun NavigationController() {
    AppTheme {
        Navigator(screen = LoginNavigation) { navigator ->
            SlideTransition(navigator)
        }
    }
}
