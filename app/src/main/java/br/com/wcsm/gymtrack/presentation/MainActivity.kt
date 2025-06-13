package br.com.wcsm.gymtrack.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.com.wcsm.gymtrack.presentation.navigation.AuthNavigation
import br.com.wcsm.gymtrack.presentation.ui.theme.BackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.GYMTrackTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            GYMTrackTheme(dynamicColor = false) {
                var isSplashScreenVisible by remember { mutableStateOf(true) }

                SetBarColor(color = BackgroundColor)

                LaunchedEffect(Unit) {
                    delay(1500)
                    isSplashScreenVisible = false
                }

                splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }

                AuthNavigation(isFirstTimeAppRunning = isFirstTime(this))
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

    private fun isFirstTime(context: Context): Boolean {
        val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean("is_first_time", true)

        if (isFirstTime) {
            preferences.edit { putBoolean("is_first_time", false) }
        }

        return isFirstTime
    }
}