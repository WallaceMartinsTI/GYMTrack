package br.com.wcsm.gymtrack.presentation.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.templates.SignUpMessageTemplate
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun SignUpMessagePage(
    onContinueClick: UnitCallback,
    onBackClick: UnitCallback
) {
    val messageText = stringResource(R.string.signup_message_3) + "\n" +
            stringResource(R.string.signup_message_4) + "\n" +
            stringResource(R.string.signup_message_5)

    SignUpMessageTemplate(
        welcomeText = stringResource(R.string.signup_message_1),
        subtitleText = stringResource(R.string.signup_message_2),
        messageText = messageText,
        finalMessage = stringResource(R.string.signup_message_6),
        onContinueButtonText = stringResource(R.string.continue_text),
        onContinueButtonIconDescription = "",
        onContinueClick = onContinueClick,
        onBackButtonText = stringResource(R.string.back_text),
        onBackClick = onBackClick
    )
}