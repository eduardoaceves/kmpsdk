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
fun NavigationController(
    user : String = "",
    password: String = "",
) {
    AppTheme {
        Navigator(screen = LoginNavigation(
            user = user,
            password = password
        )) { navigator ->
            SlideTransition(navigator)
        }
    }
}
