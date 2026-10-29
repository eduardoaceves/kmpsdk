/*
 * LoginScreen.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.kmp.module.presentation.ui.login.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.module.presentation.component.loader.Loader
import com.kmp.module.presentation.component.container.SafeScreenContainer
import com.kmp.module.presentation.component.container.SafeScreenContainerTest
import com.kmp.module.presentation.component.dialog.DialogCustom
import com.kmp.module.presentation.theme.AppTheme
import com.kmp.module.presentation.ui.login.viewmodel.LoginUiEvent
import com.kmp.module.presentation.ui.login.viewmodel.LoginViewModel
import com.kmp.module.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    
    viewModel.loginUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is LoginUiEvent.Idle -> {}
            is LoginUiEvent.LoginSuccess -> {
                viewModel.resetUiEvent()
                onLoginSuccess()
            }
        }
    }

    SafeScreenContainer(
        modifier = Modifier.background(color = AppTheme.colors.backgrounds.canvas)
    ) {
        LoginScreenContainer(
            email = loginUiState.email,
            emailError = loginUiState.emailError,
            onEmailChanged = { email ->
                viewModel.onTextChanged(email = email, password = loginUiState.password)
            },
            password = loginUiState.password,
            passwordError =  loginUiState.passwordError,
            onPasswordChanged = { password ->
                viewModel.onTextChanged(email = loginUiState.email, password = password)
            },
            passwordVisible = loginUiState.passwordVisible,
            onPasswordVisibilityChanged = { passwordVisible ->
                viewModel.updatePasswordVisible(passwordVisible = passwordVisible)
            },
            onLoginClick = {
                viewModel.validateLoginForm(
                    email = loginUiState.email,
                    password = loginUiState.password
                )
            },
            onHomeClick = {
                onHomeClick()
            },
            onCreateAccountClick = {
                onCreateAccountClick()
            },
        )
        Loader(isLoading = loginUiState.isLoading)
        DialogCustom(
            errorDialog = loginUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun LoginScreenScreenPreview() {
    SafeScreenContainerTest {
        LoginScreenContainer()
    }
}
