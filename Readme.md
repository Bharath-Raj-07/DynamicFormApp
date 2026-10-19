Lead Capture App

A configuration-driven Android application built using Kotlin and Jetpack Compose. 
It provides a responsive lead capture form with dynamic fields, validation, and submission feedback.

Tech Stack :-
- Language: Kotlin
- UI: Jetpack Compose
- Architecture: MVVM
- Dependency Injection: Koin
- Asynchronous Operations: Kotlin Coroutines and StateFlow
- Design: Material 3 with custom design tokens
- Minimum SDK: Android 8.0 (API 26)

Build and Run :-

Requirements :
- Android Studio Quail | 2026.1.1 or a compatible version
- Android SDK and platform tools installed
- JDK version compatible with the project's Gradle configuration
- An Android emulator or a physical Android device

Run using Android Studio :
1. Clone or download the project.
2. Open the project root directory in Android Studio.
3. Allow Gradle synchronization to complete.
4. Open Tools → Device Manager and create or select an Android Virtual Device.
5. For testing, use a Pixel emulator with API 35 or 36, subject to the project's installed SDKs and Gradle configuration.
6. Select the app run configuration and click Run.

Build and Run using Gradle:
From the project root, run:-
# Windows
gradlew.bat assembleDebug

# macOS/Linux
./gradlew assembleDebug

To install the debug build on a running emulator or connected device:

# Windows
gradlew.bat installDebug

# macOS/Linux
./gradlew installDebug

Alternatively, build and install in Android Studio using the Run button.


Package Layout :
The main source code is organized under:-

app/src/main/java/com/example/leadcapture/

com.example.leadcapture/
├── MainActivity.kt
├── LeadCaptureApplication.kt
├── core/
│   └── ui/
│       ├── theme/
│       │   ├── Color.kt
│       │   ├── Type.kt
│       │   ├── Theme.kt
│       │   └── AppShapes.kt
│       └── tokens/
│           ├── AppColors.kt
│           ├── AppSpacing.kt
│           ├── AppTypography.kt
│           ├── AppDimensions.kt
│           └── AppGradients.kt
├── data/
│   └── form/
│       └── LeadFormConfig.kt
├── domain/
│   └── form/
│       ├── FormModels.kt
│       └── FormValidator.kt
├── di/
│   └── AppModule.kt
└── presentation/
└── leadform/
├── LeadFormRoute.kt
├── LeadFormScreen.kt
├── LeadFormViewModel.kt
├── LeadFormState.kt
├── components/
│   ├── AppTextField.kt
│   ├── AppSelect.kt
│   ├── AppCheckbox.kt
│   ├── AppButton.kt
│   ├── FormField.kt
│   ├── AppCircularLoader.kt
│   └── AnimatedGradientCard.kt
└── layout/
├── CompactFormLayout.kt
└── ExpandedFormLayout.kt

Note: The tree above describes the intended organization. If your actual project differs, update the tree to match the files present in your repository.


Design System :
Design tokens are located in core/ui/tokens/.
- AppColors.kt: Light and dark theme colors, including the olive-green and beige-cream palette.
- AppSpacing.kt: Consistent spacing values used throughout the UI.
- AppTypography.kt: Shared typography styles.
- AppDimensions.kt: Layout breakpoints, control heights, and maximum form width.
- AppGradients.kt: Colors used for animated gradient backgrounds.
Theme definitions and font configuration live in core/ui/theme/.


UI Components :
Reusable UI atoms and form components are located in:
presentation/leadform/components/
- AppTextField.kt: Reusable text input.
- AppSelect.kt: Reusable selection control.
- AppCheckbox.kt: Reusable checkbox.
- AppButton.kt: Reusable button with loading-state support.
- AppCircularLoader.kt: Circular loading indicator.
- FormField.kt: Field molecule that combines a label, input control, hint, and validation error.
- AnimatedGradientCard.kt: Reusable animated gradient container.
These components are designed to be reusable and independent of lead-specific validation rules.
- 

Form Configuration and Validation :

1. Configuration :-
   data/form/LeadFormConfig.kt defines the form fields, their types, labels, hints, options, visibility conditions, and validation requirements.
   The configuration includes:-
   - Full name
   - Email
   - Lead type
   - Company name, conditionally displayed for company leads
   - Phone number
   - Notes, limited to 200 characters
   - Consent checkbox
   Adding or modifying fields should primarily involve updating the configuration and ensuring the appropriate generic field type is supported.

2. Models
   domain/form/FormModels.kt contains the field types, validation types, validation rules, field configuration, and conditional-visibility models.

3. Validation domain/form/FormValidator.kt contains the shared, configuration-driven validation logic.
   Validation is triggered when a field loses focus and when the user submits the form.
   Submission is blocked when visible fields contain validation errors. Hidden conditional fields do not block submission.

4. State Management and Dependency Injection
   - LeadFormViewModel.kt: Owns form values, touched fields, validation errors, and submission state.
   - LeadFormState.kt: Defines the UI states for editing, submitting, and successful submission.
   - LeadFormRoute.kt: Connects the ViewModel to the screen.
   - AppModule.kt: Registers dependencies with Koin.
   - LeadCaptureApplication.kt: Initializes Koin when the application starts.
   Kotlin Coroutines and StateFlow are used to expose observable state to the Compose UI.

5. Responsive Layouts
   - Responsive layout selection is handled in presentation/leadform/LeadFormScreen.kt, using the breakpoint defined in core/ui/tokens/AppDimensions.kt.
   - CompactFormLayout.kt: Displays fields in a single column, suitable for portrait phones and narrow windows.
   - ExpandedFormLayout.kt: Uses a two-column layout for wider screens, with designated fields spanning the full width.
   - LeadFormScreen.kt: Chooses the appropriate layout based on available width and coordinates the form screen UI.
   The layout adapts to conditional company-name visibility. The form remains scrollable on compact screens, including when the keyboard is open.

6. Submission Flow
   - The user enters lead details.
   - Field values are maintained by the ViewModel.
   - Validation runs on focus loss and on submission.
   - Invalid submissions display field-level errors.
   - Valid submissions display a loading state before the success screen.
   - The success screen presents the submitted values and allows the user to create another lead.
   The submission delay simulates a network request; no backend integration is required.

7. Scope
   - This project focuses on reusable Compose components, configuration-driven forms, validation, responsive layouts, and state management.
   - Backend integration, authentication, lead history, XML-based layouts, and cross-platform implementations are outside the current scope.
