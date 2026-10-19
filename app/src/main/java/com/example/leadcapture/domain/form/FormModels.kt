package com.example.leadcapture.domain.form

enum class FieldType {
    TEXT,
    EMAIL,
    PHONE,
    SELECT,
    TEXTAREA,
    CHECKBOX
}

enum class ValidationType {
    REQUIRED,
    EMAIL,
    PHONE_NUMBER,
    EXACT_LENGTH,
    MAX_LENGTH
}

data class ValidationRule(
    val type: ValidationType,
    val value: Int? = null,
    val message: String
)

data class FieldConfig(
    val name: String,
    val type: FieldType,
    val label: String,
    val hint: String? = null,
    val options: List<String> = emptyList(),
    val validations: List<ValidationRule> = emptyList(),
    val visibleWhen: VisibilityCondition? = null,
    val fullWidth: Boolean = false,
    val maxLength: Int? = null
)

data class VisibilityCondition(
    val fieldName: String,
    val expectedValue: String
)