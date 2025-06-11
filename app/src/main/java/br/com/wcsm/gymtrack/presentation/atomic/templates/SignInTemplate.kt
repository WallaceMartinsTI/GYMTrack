package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
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
import br.com.wcsm.gymtrack.presentation.atomic.atoms.ClearIconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.CheckboxWithTextMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.OutlinedTextFieldMolecule
import br.com.wcsm.gymtrack.presentation.model.CheckboxTextParams
import br.com.wcsm.gymtrack.presentation.model.TextFieldParams
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.GrayColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OutlineColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor
import br.com.wcsm.gymtrack.utils.UnitCallback

data class CreateAccountParams(
    val doNotHaveAccountText: String,
    val doNotHaveAccountOnClickText: String,
    val onDoNotHaveAccountClick: () -> Unit
)

@Composable
fun SignInTemplate(
    isLoading: Boolean,
    welcomeText: String,
    subtitleText: String,
    messageText: String,
    emailParams: TextFieldParams,
    passwordParams: TextFieldParams,
    keepLoggedParams: CheckboxTextParams,
    createAccountParams: CreateAccountParams,
    requestError: String?,
    onSignInClick: UnitCallback
) {
    val emailFocusRequester = remember { FocusRequester() }

    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppLogoAtom()

        TextAtom(
            text = welcomeText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(Modifier.height(8.dp))

        TextAtom(
            text = subtitleText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center
        )

        TextAtom(
            text = messageText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(280.dp)
        )

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

        TextAtom(
            text = requestError.orEmpty(),
            color = ErrorColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(280.dp)
        )

        with(keepLoggedParams) {
            CheckboxWithTextMolecule(
                text = checkboxText,
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
        }

        with(createAccountParams) {
            Row {
                TextAtom(
                    text = doNotHaveAccountText,
                    color = OnBackgroundColor,
                    fontSize = 14.sp
                )

                TextAtom(
                    text = doNotHaveAccountOnClickText,
                    color = PrimaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { onDoNotHaveAccountClick() }
                )
            }
        }

        FilledPrimaryButtonMolecule(
            onClick = onSignInClick,
            enabled = !isLoading
        ) {
            TextAtom(
                text = if(isLoading) stringResource(R.string.loading_text)
                else stringResource(R.string.signin_button_text),
                style = MaterialTheme.typography.labelMedium
            )

            IconAtom(
                imageVector = Icons.AutoMirrored.Filled.Login,
                iconDescription = stringResource(R.string.login_icon_description),
                tint = WhiteColor,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun SignInTemplateTemplate() {
    SignInTemplate(
        isLoading = false,
        welcomeText = stringResource(R.string.signin_message_1),
        subtitleText = stringResource(R.string.signin_message_2),
        messageText = stringResource(R.string.signin_message_3),
        emailParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "E-mail"
        ),
        passwordParams = TextFieldParams(
            value = "",
            onValueChange = {},
            label = "Senha"
        ),
        keepLoggedParams = CheckboxTextParams(
            checkboxText = "Continuar logado?",
            isChecked = true,
            onCheckedChange = {}
        ),
        createAccountParams = CreateAccountParams(
            doNotHaveAccountText = "Não tem uma conta?",
            doNotHaveAccountOnClickText = "Criar conta",
            onDoNotHaveAccountClick = {}
        ),
        requestError = "Falha no login",
        onSignInClick = {}
    )
}