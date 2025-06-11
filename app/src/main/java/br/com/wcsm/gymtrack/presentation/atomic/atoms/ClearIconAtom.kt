package br.com.wcsm.gymtrack.presentation.atomic.atoms

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@Composable
fun ClearIconAtom(
    onClear: () -> Unit
) {
    IconAtom(
        imageVector = Icons.Default.Clear,
        iconDescription = stringResource(R.string.clear_icon_description),
        modifier = Modifier.clickable { onClear() }
    )
}

@Preview
@Composable
private fun ClearIconAtomPreview() {
    PreviewTemplate {
        ClearIconAtom {}
    }
}