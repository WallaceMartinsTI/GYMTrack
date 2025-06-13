package br.com.wcsm.gymtrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.wcsm.gymtrack.presentation.pages.signin.SignInPage
import br.com.wcsm.gymtrack.presentation.pages.signup.SignUpMessagePage
import br.com.wcsm.gymtrack.presentation.pages.signup.SignUpPage
import br.com.wcsm.gymtrack.presentation.pages.welcome.WelcomePage
import br.com.wcsm.gymtrack.utils.commonNavComposable

@Composable
fun AuthNavigation(isFirstTimeAppRunning: Boolean) {
    val authNavController = rememberNavController()

    NavHost(
        navController = authNavController,
        startDestination = if(isFirstTimeAppRunning) AuthRoute.Welcome else AuthRoute.SignIn
    ) {
        commonNavComposable<AuthRoute.Welcome> {
            WelcomePage(onContinueClick = { authNavController.navigate(AuthRoute.SignIn) })
        }

        commonNavComposable<AuthRoute.SignIn> {
            SignInPage(
                onNavigateToHome = { authNavController.navigate(AuthRoute.AppNavigation) },
                onNavigateToSignUp = { authNavController.navigate(AuthRoute.SignUpMessage) }
            )
        }

        commonNavComposable<AuthRoute.SignUpMessage> {
            SignUpMessagePage(
                onContinueClick = {
                    authNavController.navigate(AuthRoute.SignUp) {
                        popUpTo(AuthRoute.SignUpMessage) { inclusive = true }
                    }
                },
                onBackClick = { authNavController.popBackStack() }
            )
        }

        commonNavComposable<AuthRoute.SignUp> {
            SignUpPage(
                onSignUpSuccess = { authNavController.popBackStack() },
                onBackPressed = { authNavController.popBackStack() }
            )
        }

        commonNavComposable<AuthRoute.AppNavigation> {
            AppNavigation(authNavController = authNavController)
        }
    }
}