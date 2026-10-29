/*
 * LoginViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eduardoaceves.shared.generated.resources.Accept
import com.github.eduardoaceves.shared.generated.resources.Res
import com.github.eduardoaceves.shared.generated.resources.email_wrong_format
import com.github.eduardoaceves.shared.generated.resources.empty_field
import com.github.eduardoaceves.shared.generated.resources.error
import com.github.eduardoaceves.shared.generated.resources.please_try_again_later
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.login.LoginValidationResult
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
) : ViewModel() {

    private var _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private var _loginUiEvent = MutableStateFlow<LoginUiEvent>(LoginUiEvent.Idle)
    val loginUiEvent: StateFlow<LoginUiEvent> = _loginUiEvent.asStateFlow()
    
    init{
        /*_loginUiState.update { state -> state.copy(
            email = "fintrack.postman.1790610089152@example.com",
            password = "Postman123!"
        ) }*/
    }
    
    fun onTextChanged(
        email : String,
        password : String,
    ) = viewModelScope.launch {
        when(validateLoginFormUseCase.validateLengthOnChange(
            email = email,
            password = password
        )){
            is LoginValidationResult.Success -> {
                _loginUiState.update { state -> state.copy(
                    email = email,
                    password = password,
                ) }
                loginFormErrors()
            }
            else -> {}
        }
    }
    
    fun updatePasswordVisible(passwordVisible: Boolean) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(passwordVisible = passwordVisible) }
    }

    fun validateLoginForm(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when (validateLoginFormUseCase.validateFormOnClick(
            email = email,
            password = password,
        )) {
            is LoginValidationResult.EmptyFields ->{
                loginFormErrors(
                    emailError = getString(Res.string.empty_field),
                    passwordError = getString(Res.string.empty_field)
                )
            }
            is LoginValidationResult.EmailEmpty ->{
                loginFormErrors(emailError = getString(Res.string.empty_field))
            }
            is LoginValidationResult.WrongEmailFormat -> {
                loginFormErrors(emailError = getString(Res.string.email_wrong_format))
            }
            is LoginValidationResult.PasswordEmpty -> {
                loginFormErrors(passwordError = getString(Res.string.empty_field))
            }
            else -> {
                login(email = email, password = password)
            }
        }
    }
    
    private fun loginFormErrors(
        emailError: String = "",
        passwordError: String = "",
        )
    {
        _loginUiState.update { state -> state.copy(
            emailError = emailError,
            passwordError = passwordError,
        ) }
    }

    private fun login(
        email: String,
        password: String,
    ) = viewModelScope.launch {
    
    }
    

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.Accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch  {
        _loginUiEvent.value = LoginUiEvent.Idle
    }
    
}
