package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.wcsm.gymtrack.presentation.ui.theme.BackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.GYMTrackTheme
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun PreviewTemplate(
    modifier: Modifier = Modifier,
    isColumnDirection: Boolean = true,
    content: @Composable UnitCallback
) {
    GYMTrackTheme {
        if(isColumnDirection) {
            Column(
                modifier = Modifier.fillMaxSize().background(BackgroundColor).then(modifier),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                content()
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize().background(BackgroundColor).then(modifier),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    }
}