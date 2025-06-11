package br.com.wcsm.gymtrack.presentation.atomic.atoms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.GrayColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor

@Composable
fun CheckboxAtom(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val checkboxColors = CheckboxDefaults.colors(
        checkmarkColor = WhiteColor,
        checkedColor = PrimaryColor,
        uncheckedColor = GrayColor,
        disabledCheckedColor = GrayColor,
        disabledUncheckedColor = GrayColor
    )

    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = checkboxColors,
        enabled = enabled,
        modifier = modifier
    )
}

@Preview
@Composable
private fun CheckboxAtomPreview() {
    var isChecked by remember { mutableStateOf(true) }

    PreviewTemplate {
        CheckboxAtom(
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked }
        )

        Spacer(Modifier.height(8.dp))

        CheckboxAtom(
            checked = !isChecked,
            onCheckedChange = { isChecked = !isChecked }
        )

        Spacer(Modifier.height(16.dp))

        CheckboxAtom(
            checked = isChecked,
            enabled = false,
            onCheckedChange = { isChecked = !isChecked }
        )

        Spacer(Modifier.height(8.dp))

        CheckboxAtom(
            checked = !isChecked,
            enabled = false,
            onCheckedChange = { isChecked = !isChecked }
        )
    }

}