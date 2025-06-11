package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.atoms.AppLogoAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.BlinkingTextAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.PrimaryButtonType
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun SignUpMessageTemplate(
    welcomeText: String,
    subtitleText: String,
    messageText: String,
    finalMessage: String,
    onContinueButtonText: String,
    onContinueButtonIconDescription: String,
    onContinueClick: UnitCallback,
    onBackButtonText: String,
    onBackClick: UnitCallback
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppLogoAtom()

        Spacer(Modifier.height(32.dp))

        TextAtom(
            text = welcomeText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextAtom(
            text = subtitleText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))

        TextAtom(
            text = messageText,
            color = OnBackgroundColor
        )

        Spacer(Modifier.weight(1f))

        BlinkingTextAtom(
            text = finalMessage,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FilledPrimaryButtonMolecule(onClick = onContinueClick) {
            TextAtom(
                text = onContinueButtonText.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )

            IconAtom(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                iconDescription = onContinueButtonIconDescription,
                tint = WhiteColor
            )
        }

        FilledPrimaryButtonMolecule(
            onClick = onBackClick,
            buttonType = PrimaryButtonType.NEUTRAL
        ) {
            IconAtom(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                iconDescription = onContinueButtonIconDescription,
                tint = WhiteColor
            )

            TextAtom(
                text = onBackButtonText.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun SignUpMessageTemplatePreview() {
    val messageText = stringResource(R.string.signup_message_3) + "\n" +
            stringResource(R.string.signup_message_4) + "\n" +
            stringResource(R.string.signup_message_5)

    SignUpMessageTemplate(
        welcomeText = stringResource(R.string.signup_message_1),
        subtitleText = stringResource(R.string.signup_message_2),
        messageText = messageText,
        finalMessage = stringResource(R.string.signup_message_6),
        onContinueButtonText = "continuar",
        onContinueButtonIconDescription = "",
        onContinueClick = {},
        onBackButtonText = "voltar",
        onBackClick = {}
    )
}