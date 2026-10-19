package com.example.leadcapture.presentation.leadform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.leadcapture.core.ui.theme.LeadCaptureTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LeadFormRoute(
    viewModel: LeadFormViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val formValues by viewModel.formValues.collectAsStateWithLifecycle()
    val errors by viewModel.errors.collectAsStateWithLifecycle()
    val touchedFields by viewModel.touchedFields.collectAsStateWithLifecycle()

    LeadFormScreen(
        uiState = uiState,
        formValues = formValues,
        errors = errors,
        touchedFields = touchedFields,
        onValueChange = { fieldName, value ->
            viewModel.updateField(fieldName, value)
        },
        onFieldFocusLost = viewModel::onFieldFocusLost,
        onSubmit = viewModel::submit,
        onCreateAnotherLead = viewModel::createAnotherLead
    )
}

@Preview(
    name = "Lead Form - Compact",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
private fun LeadFormPreview() {
    LeadCaptureTheme {
        LeadFormScreen(
            uiState = LeadFormUiState.Editing,
            formValues = mapOf(
                "fullName" to "Bharath",
                "email" to "bharath@example.com",
                "leadType" to "Company",
                "companyName" to "ABC Technologies",
                "phone" to "9876543210",
                "notes" to "Interested in learning more.",
                "consent" to "true"
            ),
            errors = emptyMap(),
            touchedFields = emptySet(),
            onValueChange = { _, _ -> },
            onFieldFocusLost = {},
            onSubmit = {},
            onCreateAnotherLead = {}
        )
    }
}