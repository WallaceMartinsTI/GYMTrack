package br.com.wcsm.gymtrack.presentation.pages.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.templates.WelcomeTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.GYMTrackTheme
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun WelcomePage(
    onContinueClick: UnitCallback
) {
    val featureItems = stringResource(R.string.welcome_message_3)  + "\n" +
            stringResource(R.string.welcome_message_4)  + "\n" +
            stringResource(R.string.welcome_message_5)

    GYMTrackTheme {
        WelcomeTemplate(
            welcomeText = stringResource(R.string.welcome_message_1),
            messageText = stringResource(R.string.welcome_message_2),
            featureItemsText = featureItems,
            blinkingText = stringResource(R.string.welcome_message_6),
            onContinueButtonText = stringResource(R.string.continue_text),
            onContinueButtonIconDescription = stringResource(R.string.keyboard_arrow_right_icon_description),
            onContinueButtonClick = onContinueClick
        )
    }
}