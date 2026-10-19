package com.example.leadcapture.presentation.leadform.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.leadcapture.domain.form.FieldConfig
import com.example.leadcapture.core.ui.tokens.AppSpacing

@Composable
fun ExpandedFormLayout(
    fields: List<FieldConfig>,
    modifier: Modifier = Modifier,
    fieldContent: @Composable (FieldConfig) -> Unit,
    submitContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.Large)
    ) {
        var index = 0

        while (index < fields.size) {
            val currentField = fields[index]

            if (currentField.fullWidth) {
                key(currentField.name) {
                    fieldContent(currentField)
                }

                index++
            } else {
                val nextField = fields
                    .getOrNull(index + 1)
                    ?.takeUnless { it.fullWidth }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        AppSpacing.Large
                    ),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        key(currentField.name) {
                            fieldContent(currentField)
                        }
                    }

                    if (nextField != null) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            key(nextField.name) {
                                fieldContent(nextField)
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                index += if (nextField != null) 2 else 1
            }
        }

        submitContent()
    }
}