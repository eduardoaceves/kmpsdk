/*
 * LoginUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.module.presentation.ui.login.viewmodel

sealed class LoginUiEvent {
    internal data object Idle: LoginUiEvent()
    data object LoginSuccess: LoginUiEvent()
}
