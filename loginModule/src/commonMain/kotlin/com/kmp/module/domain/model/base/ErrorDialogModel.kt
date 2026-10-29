package com.kmp.module.domain.model.base

data class ErrorDialogModel(
    val title: String = "",
    val message: String = "",
    val primaryButtonText: String = "",
    val secondaryButtonText: String = "",
)