package br.com.wcsm.gymtrack.presentation.atomic.molecules

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.presentation.atomic.atoms.CheckboxAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.GrayColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor

@Composable
fun CheckboxWithTextMolecule(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 280.dp
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, GrayColor, RoundedCornerShape(15.dp))
            .width(width)
            .selectable(
                selected = checked,
                onClick = {
                    onCheckedChange(!checked)
                },
                role = Role.Checkbox
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CheckboxAtom(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        TextAtom(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = if(checked) PrimaryColor else GrayColor
        )
    }
}

@Preview
@Composable
private fun CheckboxWithTextMoleculePreview() {
    var isChecked by remember { mutableStateOf(true) }

    PreviewTemplate {
        CheckboxWithTextMolecule(
            text = "Continuar Logado?",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked }
        )
    }
}