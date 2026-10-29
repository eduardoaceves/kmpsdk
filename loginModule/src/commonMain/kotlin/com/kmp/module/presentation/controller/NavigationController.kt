/*
 * NavigationController.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.module.presentation.controller

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.kmp.module.presentation.theme.AppTheme
import com.kmp.module.presentation.ui.login.navigation.LoginNavigation

@Composable
fun NavigationController() {
    AppTheme {
        Navigator(screen = LoginNavigation) { navigator ->
            SlideTransition(navigator)
        }
    }
}
