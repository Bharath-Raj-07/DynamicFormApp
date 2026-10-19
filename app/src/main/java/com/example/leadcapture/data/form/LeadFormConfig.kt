package com.example.leadcapture.data.form

import com.example.leadcapture.domain.form.FieldConfig
import com.example.leadcapture.domain.form.FieldType
import com.example.leadcapture.domain.form.ValidationRule
import com.example.leadcapture.domain.form.ValidationType
import com.example.leadcapture.domain.form.VisibilityCondition

object LeadFormConfig {

    val fields = listOf(

        FieldConfig(
            name = "fullName",
            type = FieldType.TEXT,
            label = "Full Name",
            hint = "Enter your full name",
            validations = listOf(
                ValidationRule(
                    type = ValidationType.REQUIRED,
                    message = "Full name is required"
                )
            )
        ),

        FieldConfig(
            name = "email",
            type = FieldType.EMAIL,
            label = "Email",
            hint = "Enter your email address",
            validations = listOf(
                ValidationRule(
                    type = ValidationType.REQUIRED,
                    message = "Email is required"
                ),
                ValidationRule(
                    type = ValidationType.EMAIL,
                    message = "Enter a valid email address"
                )
            )
        ),

        FieldConfig(
            name = "leadType",
            type = FieldType.SELECT,
            label = "Lead Type",
            hint = "Select lead type",
            options = listOf(
                "Individual",
                "Company"
            ),
            validations = listOf(
                ValidationRule(
                    type = ValidationType.REQUIRED,
                    message = "Please select a lead type"
                )
            )
        ),

        FieldConfig(
            name = "companyName",
            type = FieldType.TEXT,
            label = "Company Name",
            hint = "Enter your company name",
            visibleWhen = VisibilityCondition(
                fieldName = "leadType",
                expectedValue = "Company"
            ),
            validations = listOf(
                ValidationRule(
                    type = ValidationType.REQUIRED,
                    message = "Company name is required"
                )
            )
        ),

        FieldConfig(
            name = "phone",
            type = FieldType.PHONE,
            label = "Phone Number",
            hint = "Enter your 10-digit phone number",
            fullWidth = true,
            maxLength = 10,
            validations = listOf(
                ValidationRule(
                    type = ValidationType.REQUIRED,
                    message = "Phone number is required"
                ),
                ValidationRule(
                    type = ValidationType.PHONE_NUMBER,
                    message = "Phone number must contain 10 digits"
                )
            )
        ),

        FieldConfig(
            name = "notes",
            type = FieldType.TEXTAREA,
            label = "Notes",
            hint = "Additional information (optional)",
            fullWidth = true,
            validations = listOf(
                ValidationRule(
                    type = ValidationType.MAX_LENGTH,
                    value = 200,
                    message = "Notes cannot exceed 200 characters"
                )
            )
        ),

        FieldConfig(
            name = "consent",
            type = FieldType.CHECKBOX,
            label = "I agree to the terms and conditions",
            fullWidth = true,
            validations = listOf(
                ValidationRule(
                    type = ValidationType.REQUIRED,
                    message = "Consent is required"
                )
            )
        )
    )
}