package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.atoms.AppLogoAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.BlinkingTextAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.ui.theme.GYMTrackTheme
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun WelcomeTemplate(
    welcomeText: String,
    messageText: String,
    featureItemsText: String,
    blinkingText: String,
    onContinueButtonText: String,
    onContinueButtonIconDescription: String,
    onContinueButtonClick: UnitCallback
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogoAtom(
            modifier = Modifier
                .size(300.dp)
                .padding(top = 16.dp)
        )

        Spacer(Modifier.height(32.dp))

        TextAtom(
            text = welcomeText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(Modifier.height(16.dp))
        
        TextAtom(
            text = messageText,
            color = OnBackgroundColor,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        TextAtom(
            text = featureItemsText,
            color = OnBackgroundColor,
        )

        Spacer(Modifier.weight(1f))

        BlinkingTextAtom(
            text = blinkingText,
            color = OnBackgroundColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FilledPrimaryButtonMolecule(onClick = onContinueButtonClick) {

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
    }
}

@Preview
@Composable
private fun WelcomeTemplatePreview() {
    val featureItems = stringResource(R.string.welcome_message_3)  + "\n" +
            stringResource(R.string.welcome_message_4)  + "\n" +
            stringResource(R.string.welcome_message_5)

    GYMTrackTheme {
        WelcomeTemplate(
            welcomeText = stringResource(R.string.welcome_message_1),
            messageText = stringResource(R.string.welcome_message_2),
            featureItemsText = featureItems,
            blinkingText = stringResource(R.string.welcome_message_6),
            onContinueButtonText = "continuar",
            onContinueButtonIconDescription = "",
            onContinueButtonClick = {}
        )
    }
}