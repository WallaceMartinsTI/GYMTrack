package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.atoms.AppLogoAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.BackIconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.ClearIconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.OutlinedTextFieldMolecule
import br.com.wcsm.gymtrack.presentation.model.TextFieldParams
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun SignUpTemplate(
    isLoading: Boolean,
    pageTitle: String,
    userNameParams: TextFieldParams,
    emailParams: TextFieldParams,
    passwordParams: TextFieldParams,
    confirmPasswordParams: TextFieldParams,
    requestError: String?,
    onSignUpClick: UnitCallback,
    onBackPressed: UnitCallback
) {
    val userNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            BackIconAtom(onBack = onBackPressed)

            AppLogoAtom(modifier = Modifier.align(Alignment.Center))
        }

        TextAtom(
            text = pageTitle.uppercase(),
            color = OnBackgroundColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        with(userNameParams) {
            OutlinedTextFieldMolecule(
                modifier = Modifier.focusRequester(userNameFocusRequester),
                value = value,
                onValueChange = onValueChange,
                label = label,
                placeholder = placeholder,
                isError = isError,
                supportingText = supportingText,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    IconAtom(
                        imageVector = Icons.Default.Person,
                        iconDescription = leadingIconDescription
                    )
                },
                trailingIcon = {
                    if(value.isNotEmpty()) {
                        ClearIconAtom(
                            onClear = {
                                onClearField?.let { it() }
                                userNameFocusRequester.requestFocus()
                            }
                        )
                    }
                }
            )
        }

        with(emailParams) {
            OutlinedTextFieldMolecule(
                modifier = Modifier.focusRequester(emailFocusRequester),
                value = value,
                onValueChange = onValueChange,
                label = label,
                placeholder = placeholder,
                isError = isError,
                supportingText = supportingText,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    IconAtom(
                        imageVector = Icons.Default.Email,
                        iconDescription = leadingIconDescription
                    )
                },
                trailingIcon = {
                    if(value.isNotEmpty()) {
                        ClearIconAtom(
                            onClear = {
                                onClearField?.let { it() }
                                emailFocusRequester.requestFocus()
                            }
                        )
                    }
                }
            )
        }

        with(passwordParams) {
            OutlinedTextFieldMolecule(
                value = value,
                onValueChange = onValueChange,
                label = label,
                placeholder = placeholder,
                isError = isError,
                supportingText = supportingText,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    IconAtom(
                        imageVector = Icons.Default.Password,
                        iconDescription = leadingIconDescription
                    )
                },
                trailingIcon = {
                    IconAtom(
                        imageVector = if(isPasswordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        iconDescription = if(isPasswordVisible) stringResource(R.string.visibility_icon_description)
                        else stringResource(R.string.visibility_icon_description),
                        modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                    )
                },
                visualTransformation = if(isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }

        with(confirmPasswordParams) {
            OutlinedTextFieldMolecule(
                value = value,
                onValueChange = onValueChange,
                label = label,
                placeholder = placeholder,
                isError = isError,
                supportingText = supportingText,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    IconAtom(
                        imageVector = Icons.Default.Password,
                        iconDescription = leadingIconDescription
                    )
                },
                trailingIcon = {
                    IconAtom(
                        imageVector = if(isConfirmPasswordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        iconDescription = if(isConfirmPasswordVisible) stringResource(R.string.visibility_icon_description)
                        else stringResource(R.string.visibility_icon_description),
                        modifier = Modifier.clickable { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                    )
                },
                visualTransformation = if(isConfirmPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }

        TextAtom(
            text = requestError.orEmpty(),
            color = ErrorColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(280.dp)
        )

        FilledPrimaryButtonMolecule(
            onClick = onSignUpClick,
            enabled = !isLoading
        ) {
            TextAtom(
                text = if(isLoading) stringResource(R.string.loading_text)
                else stringResource(R.string.signup_button_text),
                style = MaterialTheme.typography.labelMedium
            )

            IconAtom(
                imageVector = Icons.Default.Save,
                iconDescription = stringResource(R.string.save_icon_description),
                tint = WhiteColor,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun SignUpTemplatePreview() {
    SignUpTemplate(
        isLoading = false,
        pageTitle = "Cadastro de conta",
        userNameParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Nome"
        ),
        emailParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Email"
        ),
        passwordParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Senha"
        ),
        confirmPasswordParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Confirmar senha"
        ),
        requestError = null,
        onSignUpClick = {},
        onBackPressed = {}
    )
}