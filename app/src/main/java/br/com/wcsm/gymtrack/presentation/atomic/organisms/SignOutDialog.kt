package br.com.wcsm.gymtrack.presentation.atomic.organisms


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.PrimaryButtonType
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.BlackColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun SignOutDialog(
    onExitApp: UnitCallback,
    onSignOut: UnitCallback,
    onDismiss: UnitCallback
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .border(1.dp, PrimaryColor, RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .background(BlackColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconAtom(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                iconDescription = stringResource(R.string.logout_icon_description),
                tint = PrimaryColor,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = stringResource(R.string.app_name),
                color = PrimaryColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.sign_out_dialog_message),
                color = WhiteColor,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            FilledPrimaryButtonMolecule(
                onClick = onSignOut,
                buttonType = PrimaryButtonType.NEGATIVE,
                modifier = Modifier.width(180.dp)
            ) {
                TextAtom(
                    text = stringResource(R.string.sign_out_dialog_finish_session).uppercase(),
                    style = MaterialTheme.typography.labelMedium
                )
            }


            FilledPrimaryButtonMolecule(
                onClick = onExitApp,
                buttonType = PrimaryButtonType.WARNING,
                modifier = Modifier.width(180.dp)
            ) {
                TextAtom(
                    text = stringResource(R.string.sign_out_dialog_exit_app).uppercase(),
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview
@Composable
private fun SignOutDialogPreview() {
    PreviewTemplate {
        SignOutDialog(
            onExitApp = {},
            onSignOut = {},
            onDismiss = {}
        )
    }
}