package br.com.wcsm.gymtrack.presentation.model

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

data class TextFieldParams(
    val value: String,
    val onValueChange: (String) -> Unit,
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val textStyle: TextStyle? = null,
    val label: String? = null,
    val placeholder: String? = null,
    val leadingIconDescription: String? = null,
    val trailingIconDescription: String? = null,
    val prefix: @Composable() (() -> Unit)? = null,
    val suffix: @Composable() (() -> Unit)? = null,
    val supportingText: String? = null,
    val isError: Boolean = false,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val singleLine: Boolean = false,
    val maxLines: Int = if (singleLine) 1 else Int. MAX_VALUE,
    val minLines: Int = 1,
    val interactionSource: MutableInteractionSource? = null,
    val shape: Shape? = null,
    val colors: TextFieldColors? = null,
    val onClearField: (() -> Unit)? = null
)
