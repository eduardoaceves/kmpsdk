/*
 * Dialog.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.module.presentation.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.module.domain.model.base.ErrorDialogModel
import com.kmp.module.presentation.component.button.TextButton
import com.kmp.module.presentation.component.text.TextNormalBold
import com.kmp.module.presentation.component.text.TextSmall
import com.kmp.module.presentation.theme.AppTheme
import com.kmp.module.presentation.theme.Dimens
import course.loginmodule.generated.resources.Res
import course.loginmodule.generated.resources.example
import org.jetbrains.compose.resources.stringResource

@Composable
fun DialogCustom(
    modifier: Modifier = Modifier,
    errorDialog: ErrorDialogModel?,
    titleTextColor: Color,
    messageTextColor: Color,
    primaryButtonBackgroundColor: Color,
    primaryButtonTextColor: Color,
    secondaryButtonBackgroundColor: Color = Color.Gray,
    secondaryButtonTextColor: Color = Color.White,
    onPrimaryButtonClick: () -> Unit = {},
    onSecondaryButtonClick: () -> Unit = {},
) {
    errorDialog?.let { errorDialog ->
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {},
            title = {
                TextNormalBold(
                    color = titleTextColor,
                    text = errorDialog.title,
                )
            },
            text = {
                TextSmall(
                    color = messageTextColor,
                    text = errorDialog.message,
                )
            },
            confirmButton = {
                if (errorDialog.primaryButtonText.isNotEmpty()) {
                    TextButton(
                        modifier = Modifier,
                        title = errorDialog.primaryButtonText,
                        fontSize =  Dimens.textSizeSmall,
                        color = AppTheme.colors.backgrounds.blue,
                        onClick = {
                            onPrimaryButtonClick()
                        }
                    )
                }
            },
            dismissButton = {
                if (errorDialog.secondaryButtonText.isNotEmpty()) {
                    TextButton(
                        modifier = Modifier,
                        title = errorDialog.secondaryButtonText,
                        fontSize =  Dimens.textSizeSmall,
                        color = AppTheme.colors.backgrounds.blue,
                        onClick = {
                            onSecondaryButtonClick()
                        }
                    )
                }
            },
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun ErrorDialogPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        DialogCustom(
            errorDialog = ErrorDialogModel(
                title = stringResource(Res.string.example),
                message = stringResource(Res.string.example),
                primaryButtonText = stringResource(Res.string.example),
            ),
            titleTextColor = Color.Black,
            messageTextColor = Color.Black,
            primaryButtonBackgroundColor = Color.Black,
            primaryButtonTextColor = Color.White,
            secondaryButtonBackgroundColor = Color.Black,
            secondaryButtonTextColor = Color.White,
        )
    }
}
