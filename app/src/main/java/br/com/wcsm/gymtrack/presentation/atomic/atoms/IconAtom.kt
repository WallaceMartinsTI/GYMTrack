package br.com.wcsm.gymtrack.presentation.atomic.atoms

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.GrayColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@Composable
fun IconAtom(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = GrayColor,
    iconDescription: String?,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = iconDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IconAtom(
    painter: Painter,
    modifier: Modifier = Modifier,
    tint: Color = GrayColor,
    iconDescription: String?,
) {
    Icon(
        painter = painter,
        contentDescription = iconDescription,
        modifier = modifier,
        tint = tint
    )
}

@Preview
@Composable
private fun IconAtomPreview() {
    PreviewTemplate {
        IconAtom(
            imageVector = Icons.Default.Settings,
            iconDescription = "Ícone de configurações",
            tint = PrimaryColor,
            modifier = Modifier.size(80.dp)
        )
    }
}