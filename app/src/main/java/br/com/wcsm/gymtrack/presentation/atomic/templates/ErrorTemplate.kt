package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.wcsm.gymtrack.presentation.atomic.molecules.ErrorContainerMolecule

@Composable
fun ErrorTemplate(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onTryAgain: (() -> Unit)? = null
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ErrorContainerMolecule(
                errorMessage = errorMessage,
                onTryAgain = onTryAgain
            )
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ErrorTemplatePreview() {
    ErrorTemplate(
        errorMessage = "Erro: não foi possível buscar treinos."
    )
}