package com.example.leadcapture.presentation.leadform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leadcapture.data.form.LeadFormConfig
import com.example.leadcapture.domain.form.FieldType
import com.example.leadcapture.domain.form.validateForm
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class LeadFormViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LeadFormUiState>(LeadFormUiState.Editing)
    val uiState: StateFlow<LeadFormUiState> = _uiState.asStateFlow()

    private val _formValues = MutableStateFlow(initialFormValues())
    val formValues: StateFlow<Map<String, String>> = _formValues.asStateFlow()

    private val _errors = MutableStateFlow<Map<String, String>>(emptyMap())
    val errors: StateFlow<Map<String, String>> = _errors.asStateFlow()

    private val _touchedFields = MutableStateFlow<Set<String>>(emptySet())
    val touchedFields: StateFlow<Set<String>> = _touchedFields.asStateFlow()

    private fun initialFormValues(): Map<String, String> =
        LeadFormConfig.fields.associate { field ->
            field.name to if (field.type == FieldType.CHECKBOX) {
                "false"
            } else {
                ""
            }
        }

    fun updateField(
        fieldName: String,
        value: String
    ) {
        _formValues.value += (fieldName to value)

        if (_uiState.value is LeadFormUiState.Submitted) {
            _uiState.value = LeadFormUiState.Editing
        }

        if (fieldName in _touchedFields.value) {
            _errors.value = validateForm(
                fields = LeadFormConfig.fields,
                values = _formValues.value
            )
        }
    }

    fun submit() {
        viewModelScope.launch {
            _uiState.value = LeadFormUiState.Submitting

            val currentValues = _formValues.value
            _touchedFields.value += LeadFormConfig.fields.map { it.name }

            val validationErrors = validateForm(
                fields = LeadFormConfig.fields,
                values = currentValues
            )

            _errors.value = validationErrors
            if (validationErrors.isNotEmpty()) {
                _uiState.value = LeadFormUiState.Editing
                return@launch
            }
            delay(2_000L.milliseconds)
            _uiState.value = LeadFormUiState.Submitted(
                values = currentValues
            )
        }
    }

    fun createAnotherLead() {
        _formValues.value = initialFormValues()
        _errors.value = emptyMap()
        _touchedFields.value = emptySet()
        _uiState.value = LeadFormUiState.Editing
    }

    fun onFieldFocusLost(fieldName: String) {
        _touchedFields.value += fieldName

        _errors.value = validateForm(
            fields = LeadFormConfig.fields,
            values = _formValues.value
        )
    }
}