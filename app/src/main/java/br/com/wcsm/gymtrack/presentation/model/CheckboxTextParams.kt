package br.com.wcsm.gymtrack.presentation.model

data class CheckboxTextParams(
    val checkboxText: String,
    val isChecked: Boolean,
    val onCheckedChange: (Boolean) -> Unit
)
