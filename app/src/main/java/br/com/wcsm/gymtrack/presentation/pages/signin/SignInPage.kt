package br.com.wcsm.gymtrack.presentation.pages.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.templates.CreateAccountParams
import br.com.wcsm.gymtrack.presentation.atomic.templates.SignInTemplate
import br.com.wcsm.gymtrack.presentation.model.CheckboxTextParams
import br.com.wcsm.gymtrack.presentation.model.TextFieldParams
import br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel.SignInState
import br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel.SignInViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInPage(
    onNavigateToHome: UnitCallback,
    onNavigateToSignUp: UnitCallback
) {
    val signInViewModel: SignInViewModel = koinViewModel()

    val uiState by signInViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(signInViewModel) {
        signInViewModel.state.collectLatest { state ->
            when(state) {
                SignInState.GoToHome -> onNavigateToHome()
                SignInState.GoToSignUp -> onNavigateToSignUp()
            }
        }
    }

    SignInTemplate(
        isLoading = uiState.isLoading,
        welcomeText = stringResource(R.string.signin_message_1),
        subtitleText = stringResource(R.string.signin_message_2),
        messageText = stringResource(R.string.signin_message_3),
        emailParams = TextFieldParams(
            value = uiState.email,
            onValueChange = signInViewModel::handleEmail,
            label = stringResource(R.string.email_label),
            placeholder = stringResource(R.string.email_placeholder),
            isError = uiState.emailErrorMessage.isNotBlank(),
            supportingText = uiState.emailErrorMessage,
            leadingIconDescription = stringResource(R.string.email_icon_description),
            onClearField = { signInViewModel.handleEmail("") }
        ),
        passwordParams = TextFieldParams(
            value = uiState.password,
            onValueChange = signInViewModel::handlePassword,
            label = stringResource(R.string.password_label),
            placeholder = stringResource(R.string.password_placeholder),
            isError = uiState.passwordErrorMessage.isNotBlank(),
            supportingText = uiState.passwordErrorMessage,
            leadingIconDescription = stringResource(R.string.password_icon_description)
        ),
        keepLoggedParams = CheckboxTextParams(
            checkboxText = stringResource(R.string.signin_keep_login),
            isChecked = uiState.keepLogin,
            onCheckedChange = signInViewModel::handleKeepLogin
        ),
        createAccountParams = CreateAccountParams(
            doNotHaveAccountText = stringResource(R.string.signin_do_not_have_account),
            doNotHaveAccountOnClickText = stringResource(R.string.signin_do_not_have_account_button),
            onDoNotHaveAccountClick = signInViewModel::handleSignUp
        ),
        requestError = uiState.error,
        onSignInClick = signInViewModel::signIn
    )
}