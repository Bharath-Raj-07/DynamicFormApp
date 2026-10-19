package com.example.leadcapture.presentation.leadform

sealed interface LeadFormUiState {

    data object Editing : LeadFormUiState

    data object Submitting : LeadFormUiState

    data class Submitted(
        val values: Map<String, String>
    ) : LeadFormUiState
}