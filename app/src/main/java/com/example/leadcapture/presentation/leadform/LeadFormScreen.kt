package com.example.leadcapture.presentation.leadform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leadcapture.data.form.LeadFormConfig
import com.example.leadcapture.domain.form.FieldConfig
import com.example.leadcapture.domain.form.FieldType
import com.example.leadcapture.presentation.leadform.components.AnimatedGradientCard
import com.example.leadcapture.presentation.leadform.components.AppButton
import com.example.leadcapture.presentation.leadform.components.AppCheckbox
import com.example.leadcapture.presentation.leadform.components.AppSelect
import com.example.leadcapture.presentation.leadform.components.AppTextField
import com.example.leadcapture.presentation.leadform.layout.CompactFormLayout
import com.example.leadcapture.presentation.leadform.layout.ExpandedFormLayout
import com.example.leadcapture.core.ui.tokens.AppDimensions
import com.example.leadcapture.core.ui.tokens.AppShapes
import com.example.leadcapture.core.ui.tokens.AppSpacing

@Composable
fun LeadFormScreen(
    uiState: LeadFormUiState,
    formValues: Map<String, String>,
    errors: Map<String, String>,
    touchedFields: Set<String>,
    onValueChange: (String, String) -> Unit,
    onFieldFocusLost: (String) -> Unit,
    onSubmit: () -> Unit,
    onCreateAnotherLead: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        when (uiState) {
            LeadFormUiState.Editing,
            LeadFormUiState.Submitting -> {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .imePadding()
                ) {
                    val isExpanded = maxWidth >= AppDimensions.ExpandedBreakpoint

                    val visibleFields = LeadFormConfig.fields.filter { field ->
                        val condition = field.visibleWhen

                        condition == null ||
                                formValues[condition.fieldName] == condition.expectedValue
                    }

                    val fieldContent: @Composable (FieldConfig) -> Unit =
                        { field ->
                            LeadFormField(
                                field = field,
                                formValues = formValues,
                                errors = errors,
                                touchedFields = touchedFields,
                                onValueChange = { value ->
                                    onValueChange(field.name, value)
                                },
                                onFocusLost = {
                                    onFieldFocusLost(field.name)
                                }
                            )
                        }

                    val submitContent: @Composable () -> Unit = {
                        AppButton(
                            text = "Submit",
                            onClick = onSubmit,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState != LeadFormUiState.Submitting,
                            isLoading = uiState == LeadFormUiState.Submitting
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(AppSpacing.Large),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = AppDimensions.FormMaxWidth),
                            shape = AppShapes.Card,
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            )
                        ) {
                            AnimatedGradientCard{
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(AppSpacing.XLarge),
                                    verticalArrangement = Arrangement.spacedBy(AppSpacing.Large)
                                ) {
                                    Text(
                                        text = "Create Lead",
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    Text(
                                        text = "Enter the details below to create a new lead.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    if (isExpanded) {
                                        ExpandedFormLayout(
                                            fields = visibleFields,
                                            modifier = Modifier.fillMaxWidth(),
                                            fieldContent = fieldContent,
                                            submitContent = submitContent
                                        )
                                    } else {
                                        CompactFormLayout(
                                            fields = visibleFields,
                                            modifier = Modifier.fillMaxWidth(),
                                            fieldContent = fieldContent,
                                            submitContent = submitContent
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is LeadFormUiState.Submitted -> {
                SubmittedLeadContent(
                    values = uiState.values,
                    onCreateAnother = onCreateAnotherLead,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun LeadFormField(
    field: FieldConfig,
    formValues: Map<String, String>,
    errors: Map<String, String>,
    touchedFields: Set<String>,
    onValueChange: (String) -> Unit,
    onFocusLost: () -> Unit
) {
    val value = formValues[field.name].orEmpty()
    val error = errors[field.name]
        .takeIf { field.name in touchedFields }

    when (field.type) {

        FieldType.TEXT -> {
            AppTextField(
                value = value,
                onValueChange = { newValue ->
                    val maxLength = field.maxLength

                    if (maxLength == null || newValue.length <= maxLength) {
                        onValueChange(newValue)
                    }
                },
                label = field.label,
                placeholder = field.hint,
                error = error,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                onFocusLost = onFocusLost
            )
        }

        FieldType.PHONE -> {
            AppTextField(
                value = value,
                onValueChange = { newValue ->
                    val digitsOnly = newValue.filter { it.isDigit() }
                    val maxLength = field.maxLength

                    if (maxLength == null || digitsOnly.length <= maxLength) {
                        onValueChange(digitsOnly)
                    }
                },
                label = field.label,
                placeholder = field.hint,
                error = error,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                onFocusLost = onFocusLost
            )
        }

        FieldType.EMAIL -> {
            AppTextField(
                value = value,
                onValueChange = onValueChange,
                label = field.label,
                placeholder = field.hint,
                error = error,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                onFocusLost = onFocusLost
            )
        }

        FieldType.SELECT -> {
            AppSelect(
                value = value,
                onValueChange = onValueChange,
                label = field.label,
                options = field.options,
                placeholder = field.hint,
                error = error,
                modifier = Modifier.fillMaxWidth()
            )
        }

        FieldType.TEXTAREA -> {
            AppTextField(
                value = value,
                onValueChange = { newValue ->
                    val maxLength = field.maxLength

                    if (maxLength == null || newValue.length <= maxLength) {
                        onValueChange(newValue)
                    }
                },
                label = field.label,
                placeholder = field.hint,
                error = error,
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                onFocusLost = onFocusLost
            )
        }

        FieldType.CHECKBOX -> {
            AppCheckbox(
                checked = value == "true",
                onCheckedChange = { checked ->
                    onValueChange(checked.toString())
                    onFocusLost()
                },
                label = field.label,
                error = error,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SubmittedLeadContent(
    values: Map<String, String>,
    onCreateAnother: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(AppSpacing.Large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = AppDimensions.FormMaxWidth),
            shape = AppShapes.Card,
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            AnimatedGradientCard {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(AppSpacing.Large),
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.Large)
                ) {
                    Text(
                        text = "Lead Submitted",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Your lead details have been submitted successfully.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppSpacing.Large),
                            verticalArrangement = Arrangement.spacedBy(AppSpacing.Medium)
                        ) {
                            val displayFields = LeadFormConfig.fields.filter { field ->
                                val condition = field.visibleWhen

                                (condition == null ||
                                        values[condition.fieldName] == condition.expectedValue) &&
                                        field.type != FieldType.CHECKBOX
                            }

                            displayFields.forEach { field ->
                                val value = values[field.name].orEmpty()

                                if (value.isNotBlank()) {
                                    Column {
                                        Text(
                                            text = field.label,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(AppSpacing.XSmall))
                                        Text(
                                            text = value,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                }
                            }

                            val consentGiven = values["consent"] == "true"

                            if (consentGiven) {
                                Text(
                                    text = "Consent: Given",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    AppButton(
                        text = "Create Another Lead",
                        onClick = onCreateAnother,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }
}