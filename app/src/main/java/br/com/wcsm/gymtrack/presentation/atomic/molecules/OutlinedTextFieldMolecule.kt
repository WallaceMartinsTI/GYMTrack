package br.com.wcsm.gymtrack.presentation.atomic.molecules

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OutlineColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryContainerColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WarningColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor

@Composable
fun OutlinedTextFieldMolecule(
    modifier: Modifier = Modifier,
    width: Dp = 280.dp,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    prefix: @Composable() (() -> Unit)? = null,
    suffix: @Composable() (() -> Unit)? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int. MAX_VALUE,
    minLines: Int = 1
) {
    val outlinedTextFieldColors = OutlinedTextFieldDefaults.colors(
        cursorColor = PrimaryColor,
        focusedBorderColor = PrimaryContainerColor,
        focusedLabelColor = PrimaryColor,
        focusedTextColor = OnBackgroundColor,
        unfocusedTextColor = WhiteColor,
        errorTextColor = OnBackgroundColor
    )

    OutlinedTextField(
        modifier = modifier.width(width),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 2.sp
        ),
        label = {
            if(label != null) {
                TextAtom(
                    text = label,
                    color = WarningColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        placeholder = {
            TextAtom(
                text = placeholder ?: "",
                color = OutlineColor
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = {
            TextAtom(
                text = supportingText ?: "",
                color = if(isError) ErrorColor else WarningColor
            )
        },
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        colors = outlinedTextFieldColors
    )
}

@Preview(apiLevel = 34)
@Composable
private fun OutlinedTextFieldMoleculePreview() {
    PreviewTemplate {
        var value by remember { mutableStateOf("") }

        OutlinedTextFieldMolecule(
            value = value,
            onValueChange = { value = it },
            label = "Teste",
            placeholder = "Digite algo..."
        )
    }
}