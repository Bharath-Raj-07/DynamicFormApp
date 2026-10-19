package com.example.leadcapture.presentation.leadform.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.leadcapture.core.ui.tokens.AppShapes
import com.example.leadcapture.core.ui.tokens.AppSpacing

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    error: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onFocusLost: (() -> Unit)? = null
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    var wasFocused by remember {
        mutableStateOf(false)
    }

    Modifier.onFocusChanged { focusState ->
        if (focusState.isFocused) {
            wasFocused = true
        } else if (wasFocused) {
            wasFocused = false
            onFocusLost?.invoke()
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            label = {
                Text(text = label)
            },
            placeholder = placeholder?.let {
                {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = trailingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null
                    )
                }
            },
            isError = error != null,
            enabled = enabled,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            shape = AppShapes.Input,
            colors = colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorLabelColor = MaterialTheme.colorScheme.error
            )
        )

        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(
                    start = AppSpacing.Small,
                    top = AppSpacing.XSmall
                )
            )
        }
    }
}