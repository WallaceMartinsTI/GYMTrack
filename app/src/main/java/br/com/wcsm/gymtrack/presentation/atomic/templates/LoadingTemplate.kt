package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.wcsm.gymtrack.presentation.atomic.atoms.LoadingAtom
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@Composable
fun LoadingTemplate(
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            LoadingAtom(color = PrimaryColor)
        }
    }
}

@Preview
@Composable
private fun LoadingTemplatePreview() {
    LoadingTemplate()
}