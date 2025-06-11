package br.com.wcsm.gymtrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.wcsm.gymtrack.presentation.pages.home.HomePage
import br.com.wcsm.gymtrack.presentation.pages.signin.SignInPage
import br.com.wcsm.gymtrack.presentation.pages.signup.SignUpMessagePage
import br.com.wcsm.gymtrack.presentation.pages.signup.SignUpPage
import br.com.wcsm.gymtrack.presentation.pages.welcome.WelcomePage

@Composable
fun AppNavigation(isFirstTimeAppRunning: Boolean) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Welcome
    ) {
        composable<Route.Welcome> {
            WelcomePage(onContinueClick = { navController.navigate(Route.SignIn) })
        }

        composable<Route.SignIn> {
            SignInPage(
                onNavigateToHome = { navController.navigate(Route.Home) },
                onNavigateToSignUp = { navController.navigate(Route.SignUpMessage) }
            )
        }

        composable<Route.SignUpMessage> {
            SignUpMessagePage(
                onContinueClick = {
                    navController.navigate(Route.SignUp) {
                        popUpTo(Route.SignUpMessage) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<Route.SignUp> {
            SignUpPage(
                onSignUpSuccess = { navController.popBackStack() },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable<Route.Home> {
            HomePage()
        }
    }
}