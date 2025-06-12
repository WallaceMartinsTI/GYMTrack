package br.com.wcsm.gymtrack.presentation.atomic.atoms

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.OnPrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@Composable
fun FloatingActionButtonAtom(
    icon: Painter,
    iconDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier,
        containerColor = PrimaryColor,
        contentColor = OnPrimaryColor
    ) {
        IconAtom(
            painter = icon,
            iconDescription = iconDescription,
            tint = OnPrimaryColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun FloatingActionButtonAtom(
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier,
        containerColor = PrimaryColor,
        contentColor = OnPrimaryColor
    ) {
        IconAtom(
            imageVector = icon,
            iconDescription = iconDescription,
            tint = OnPrimaryColor
        )
    }
}

@Preview(apiLevel = 34)
@Composable
private fun FloatingActionButtonAtomPreview() {
    PreviewTemplate {
        FloatingActionButtonAtom(
            icon = Icons.Default.Add,
            iconDescription = "",
            onClick = {}
        )
    }
}