package com.example.leadcapture.presentation.leadform.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.leadcapture.core.ui.tokens.AppShapes
import com.example.leadcapture.core.ui.tokens.AppSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSelect(
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    error: String? = null,
    enabled: Boolean = true
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (enabled) expanded = !expanded
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                label = {
                    Text(label)
                },
                placeholder = placeholder?.let {
                    {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                isError = error != null,
                shape = AppShapes.Input,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    errorBorderColor = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(option)
                        },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

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