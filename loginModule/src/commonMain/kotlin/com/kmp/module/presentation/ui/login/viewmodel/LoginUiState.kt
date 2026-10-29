/*
 * LoginUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.module.presentation.ui.login.viewmodel

import com.kmp.module.domain.model.base.ErrorDialogModel
import com.kmp.module.utils.operators.StatusLoading

data class LoginUiState(
    val email : String = "",
    val emailError : String = "",
    val password : String = "",
    val passwordError : String = "",
    val passwordVisible : Boolean = false,
    val errorDialog : ErrorDialogModel? = null,
    val isLoading : StatusLoading = StatusLoading.DISMISS_LOADING,
)
