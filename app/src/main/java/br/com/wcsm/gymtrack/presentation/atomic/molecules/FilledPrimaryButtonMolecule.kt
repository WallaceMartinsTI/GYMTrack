package br.com.wcsm.gymtrack.presentation.atomic.molecules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.Gray04Color
import br.com.wcsm.gymtrack.presentation.ui.theme.GrayColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WarningColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor

enum class PrimaryButtonType {
    POSITIVE, NEGATIVE, WARNING, NEUTRAL
}

@Composable
fun FilledPrimaryButtonMolecule(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 280.dp,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    buttonType: PrimaryButtonType = PrimaryButtonType.POSITIVE,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable() (RowScope.() -> Unit)
) {
    val buttonColors = ButtonDefaults.buttonColors(
        contentColor = WhiteColor,
        containerColor = when(buttonType) {
            PrimaryButtonType.POSITIVE -> PrimaryColor
            PrimaryButtonType.NEGATIVE -> ErrorColor
            PrimaryButtonType.WARNING ->  WarningColor
            PrimaryButtonType.NEUTRAL -> GrayColor
        },
        disabledContentColor = WhiteColor,
        disabledContainerColor = Gray04Color
    )

    Button(
        onClick = onClick,
        modifier = modifier.width(width),
        enabled = enabled,
        shape = shape,
        colors = buttonColors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        content()
    }
}

@Preview(apiLevel = 34)
@Composable
private fun FilledPrimaryButtonMoleculePreview() {
    PreviewTemplate {
        FilledPrimaryButtonMolecule(
            onClick = {},
            enabled = true,
            buttonType = PrimaryButtonType.POSITIVE
        ) {
            Text("POSITIVE")
        }

        FilledPrimaryButtonMolecule(
            onClick = {},
            enabled = true,
            buttonType = PrimaryButtonType.NEGATIVE
        ) {
            Text("NEGATIVE")
        }

        FilledPrimaryButtonMolecule(
            onClick = {},
            enabled = true,
            buttonType = PrimaryButtonType.WARNING
        ) {
            Text("WARNING")
        }

        FilledPrimaryButtonMolecule(
            onClick = {},
            enabled = true,
            buttonType = PrimaryButtonType.NEUTRAL
        ) {
            Text("NEUTRAL")
        }

        FilledPrimaryButtonMolecule(
            onClick = {},
            enabled = false
        ) {
            Text("DISABLED")
        }
    }
}