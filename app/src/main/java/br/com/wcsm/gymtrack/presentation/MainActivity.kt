package br.com.wcsm.gymtrack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import br.com.wcsm.gymtrack.presentation.navigation.AppNavigation
import br.com.wcsm.gymtrack.presentation.ui.theme.BackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.GYMTrackTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GYMTrackTheme(dynamicColor = false) {
                SetBarColor(color = BackgroundColor)

                AppNavigation(true)
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }
}