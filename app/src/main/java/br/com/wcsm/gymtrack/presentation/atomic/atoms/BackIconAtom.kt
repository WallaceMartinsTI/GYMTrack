package br.com.wcsm.gymtrack.presentation.atomic.atoms

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@Composable
fun BackIconAtom(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    IconAtom(
        imageVector = Icons.Default.ArrowBackIosNew,
        iconDescription = stringResource(R.string.arrow_back_ios_new_icon_description),
        tint = PrimaryColor,
        modifier = modifier.clickable { onBack() }
    )
}

@Preview
@Composable
private fun BackIconAtomPreview() {
    PreviewTemplate {
        BackIconAtom {}
    }
}