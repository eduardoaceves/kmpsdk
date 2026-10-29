/*
 * ValidateLoginFormUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.kmp.module.domain.usecase.login

import com.kmp.module.utils.constant.Constants
import com.kmp.module.utils.text.validateEmailFormat
import org.koin.core.annotation.Factory

@Factory
class ValidateLoginFormUseCase {
    
    fun validateFormOnClick(
        email : String,
        password : String
    ) : LoginValidationResult{
        return when {
            email.isBlank() && password.isBlank() ->
                LoginValidationResult.EmptyFields
            email.isBlank() ->
                LoginValidationResult.EmailEmpty
            password.isBlank() ->
                LoginValidationResult.PasswordEmpty
            !email.validateEmailFormat() ->
                LoginValidationResult.WrongEmailFormat
            else ->
                LoginValidationResult.Success
        }
    }
    
    fun validateLengthOnChange(
        email : String = "",
        password: String = ""
    ) : LoginValidationResult
    {
        return when {
            password.length > Constants.PASSWORD_LENGTH ->
                LoginValidationResult.PasswordWrongLength
            email.length > Constants.EMAIL_LENGTH ->
                LoginValidationResult.EmailWrongLength
            else -> LoginValidationResult.Success
        }
    }
    
    
}
