package br.com.wcsm.gymtrack.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.PrimaryButtonType
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.BlackColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmActionDialog(
    dialogTitle: String,
    dialogMessage: String,
    firstButtonText: String,
    firstButtonType: PrimaryButtonType,
    onFirstButtonClick: () -> Unit,
    secondButtonText: String? = null,
    secondButtonType: PrimaryButtonType? = null,
    onSecondButtonClick: (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    val isThereSecondButton = secondButtonText != null
            && secondButtonType != null
            && onSecondButtonClick != null

    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, PrimaryColor, RoundedCornerShape(16.dp))
            .background(BlackColor)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextAtom(
                text = dialogTitle,
                color = PrimaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            TextAtom(
                text = dialogMessage,
                color = OnBackgroundColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(16.dp))

            FilledPrimaryButtonMolecule(
                onClick = {
                    onFirstButtonClick()
                    onDismiss()
                },
                buttonType = firstButtonType
            ) {
                TextAtom(
                    text = firstButtonText.uppercase(),
                    fontWeight = FontWeight.SemiBold
                )
            }

            if(isThereSecondButton) {
                FilledPrimaryButtonMolecule(
                    onClick = {
                        onSecondButtonClick!!()
                        onDismiss()
                    },
                    buttonType = secondButtonType!!
                ) {
                    TextAtom(
                        text = secondButtonText!!.uppercase(),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ConfirmActionDialogPreview() {
    PreviewTemplate {
        ConfirmActionDialog(
            dialogTitle = "TÍTULO",
            dialogMessage = "Você tem certeza que deseja excluir ... ?",
            firstButtonText = "CONFIRMAR",
            firstButtonType = PrimaryButtonType.WARNING,
            onFirstButtonClick = {},
            secondButtonText = "CANCELAR",
            secondButtonType = PrimaryButtonType.NEUTRAL,
            onSecondButtonClick = {},
            onDismiss = {}
        )
    }
}