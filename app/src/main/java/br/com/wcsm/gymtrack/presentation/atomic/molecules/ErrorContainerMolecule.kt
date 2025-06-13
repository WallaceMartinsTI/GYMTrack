package br.com.wcsm.gymtrack.presentation.atomic.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.templates.PreviewTemplate
import br.com.wcsm.gymtrack.presentation.ui.theme.BlackColor
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor

@Composable
fun ErrorContainerMolecule(
    errorMessage: String,
    modifier: Modifier = Modifier,
    onTryAgain: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(BlackColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconAtom(
            imageVector = Icons.Default.ErrorOutline,
            iconDescription = stringResource(R.string.error_outline_icon_descriptiob),
            tint = ErrorColor,
            modifier = Modifier.size(42.dp)
        )
        TextAtom(
            text = stringResource(R.string.oops_error_label),
            color = ErrorColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        TextAtom(
            text = errorMessage,
            color = OnBackgroundColor,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(250.dp)
        )

        if(onTryAgain != null) {
            FilledPrimaryButtonMolecule(
                onClick = onTryAgain,
                width = 200.dp,
                modifier = Modifier
                    .scale(0.8f)
                    .padding(top = 16.dp)
            ) {
                TextAtom(
                    text = stringResource(R.string.try_again_text).uppercase()
                )
            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ErrorContainerMoleculePreview() {
    PreviewTemplate {
        ErrorContainerMolecule(
            errorMessage = "Erro: não foi possível buscar treinos e essa mensagem está muito grande vamos ver como fica",
            onTryAgain = {}
        )
    }
}