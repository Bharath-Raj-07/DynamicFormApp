package com.example.leadcapture.presentation.leadform.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.leadcapture.domain.form.FieldConfig
import com.example.leadcapture.core.ui.tokens.AppSpacing

@Composable
fun CompactFormLayout(
    fields: List<FieldConfig>,
    modifier: Modifier = Modifier,
    fieldContent: @Composable (FieldConfig) -> Unit,
    submitContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.Large)
    ) {
        fields.forEach { field ->
            fieldContent(field)
        }

        submitContent()
    }
}