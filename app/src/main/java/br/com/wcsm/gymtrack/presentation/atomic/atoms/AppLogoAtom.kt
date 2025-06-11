package br.com.wcsm.gymtrack.presentation.atomic.atoms

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate

@Composable
fun AppLogoAtom(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.gym_track_logo),
        contentDescription = stringResource(R.string.gym_track_logo_description),
        modifier = modifier
    )
}

@Preview
@Composable
private fun AppLogoAtomPreview() {
    PreviewTemplate {
        AppLogoAtom()
    }
}