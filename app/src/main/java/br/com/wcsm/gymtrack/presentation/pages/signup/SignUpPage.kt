package br.com.wcsm.gymtrack.presentation.pages.signup

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.templates.SignUpTemplate
import br.com.wcsm.gymtrack.presentation.model.TextFieldParams
import br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel.SignUpState
import br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel.SignUpViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import br.com.wcsm.gymtrack.utils.showToastMessage
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpPage(
    onSignUpSuccess: UnitCallback,
    onBackPressed: UnitCallback
) {
    val context = LocalContext.current

    val signUpViewModel: SignUpViewModel = koinViewModel()

    val uiState by signUpViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(signUpViewModel) {
        signUpViewModel.state.collectLatest { state ->
            when(state) {
                SignUpState.SignUpSuccess -> {
                    (context as? Activity)?.showToastMessage(
                        message = "Conta criada com sucesso!"
                    )
                    onSignUpSuccess()
                }
            }
        }
    }

    SignUpTemplate(
        isLoading = uiState.isLoading,
        pageTitle = stringResource(R.string.signup_page_title),
        userNameParams = TextFieldParams(
            value = uiState.signUpParams.userName,
            onValueChange = signUpViewModel::handleUserName,
            label = stringResource(R.string.signup_user_name_label),
            placeholder = stringResource(R.string.signup_user_name_placeholder),
            isError = uiState.signUpParams.userNameErrorMessage.isNotBlank(),
            supportingText = uiState.signUpParams.userNameErrorMessage,
            leadingIconDescription = stringResource(R.string.person_icon_description),
            onClearField = { signUpViewModel.handleUserName("") }
        ),
        emailParams = TextFieldParams(
            value = uiState.signUpParams.email,
            onValueChange = signUpViewModel::handleEmail,
            label = stringResource(R.string.email_label),
            placeholder = stringResource(R.string.email_placeholder),
            isError = uiState.signUpParams.emailErrorMessage.isNotBlank(),
            supportingText = uiState.signUpParams.emailErrorMessage,
            leadingIconDescription = stringResource(R.string.email_icon_description),
            onClearField = { signUpViewModel.handleEmail("") }
        ),
        passwordParams = TextFieldParams(
            value = uiState.signUpParams.password,
            onValueChange = signUpViewModel::handlePassword,
            label = stringResource(R.string.password_label),
            placeholder = stringResource(R.string.password_placeholder),
            isError = uiState.signUpParams.passwordErrorMessage.isNotBlank(),
            supportingText = uiState.signUpParams.passwordErrorMessage,
            leadingIconDescription = stringResource(R.string.password_icon_description)
        ),
        confirmPasswordParams = TextFieldParams(
            value = uiState.signUpParams.confirmPassword,
            onValueChange = signUpViewModel::handleConfirmPassword,
            label = stringResource(R.string.signup_confirm_password_label),
            placeholder = stringResource(R.string.signup_confirm_password_placeholder),
            isError = uiState.signUpParams.confirmPasswordErrorMessage.isNotBlank(),
            supportingText = uiState.signUpParams.confirmPasswordErrorMessage,
            leadingIconDescription = stringResource(R.string.password_icon_description)
        ),
        requestError = uiState.error,
        onSignUpClick = signUpViewModel::signUp,
        onBackPressed = onBackPressed
    )
}