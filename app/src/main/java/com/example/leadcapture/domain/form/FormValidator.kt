package com.example.leadcapture.domain.form

fun validateForm(
    fields: List<FieldConfig>,
    values: Map<String, String>
): Map<String, String> {

    val errors = mutableMapOf<String, String>()

    fields.forEach { field ->

        // Ignore fields that are currently hidden.
        val condition = field.visibleWhen

        if (condition != null) {
            val actualValue = values[condition.fieldName].orEmpty()

            if (actualValue != condition.expectedValue) {
                return@forEach
            }
        }

        val value = values[field.name].orEmpty()

        for (rule in field.validations) {

            val error = when (rule.type) {

                ValidationType.REQUIRED -> {
                    when (field.type) {
                        FieldType.CHECKBOX -> {
                            if (value != "true") rule.message else null
                        }

                        FieldType.SELECT -> {
                            if (value.isBlank()) rule.message else null
                        }

                        else -> {
                            if (value.isBlank()) rule.message else null
                        }
                    }
                }

                ValidationType.EMAIL -> {
                    val validEmail = Regex(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                    ).matches(value)
                    if (value.isNotBlank() && !validEmail) {
                        rule.message
                    } else {
                        null
                    }
                }

                ValidationType.PHONE_NUMBER -> {
                    if (value.isNotBlank() &&
                        (value.length != 10 || value.any { !it.isDigit() })
                    ) {
                        rule.message
                    } else {
                        null
                    }
                }

                ValidationType.EXACT_LENGTH -> {
                    if (
                        value.isNotBlank() &&
                        rule.value != null &&
                        value.length != rule.value
                    ) {
                        rule.message
                    } else {
                        null
                    }
                }

                ValidationType.MAX_LENGTH -> {
                    if (
                        rule.value != null &&
                        value.length > rule.value
                    ) {
                        rule.message
                    } else {
                        null
                    }
                }
            }

            if (error != null) {
                errors[field.name] = error
                break
            }
        }

        if (
            field.name == "phone" &&
            value.isNotBlank() &&
            value.any { !it.isDigit() }
        ) {
            errors[field.name] =
                "Phone number must contain digits only"
        }
    }

    return errors
}