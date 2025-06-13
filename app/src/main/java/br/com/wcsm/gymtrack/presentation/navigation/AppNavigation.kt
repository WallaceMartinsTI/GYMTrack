package br.com.wcsm.gymtrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.pages.home.HomePage
import br.com.wcsm.gymtrack.presentation.pages.workout.WorkoutFormPage
import br.com.wcsm.gymtrack.presentation.pages.workout.WorkoutPage
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutViewModel
import br.com.wcsm.gymtrack.utils.commonNavComposable
import br.com.wcsm.gymtrack.utils.decodeStringInKObject
import br.com.wcsm.gymtrack.utils.encodeKObjectInString
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraphBuilder.AppNavigation(
    authNavController: NavController
) {
    val navController = rememberNavController()

    val workoutViewModel: WorkoutViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        commonNavComposable<Route.Home> {
            HomePage(
                workoutViewModel = workoutViewModel,
                onNavigateToWorkout = { workout ->
                    navController.navigate(Route.Workout(workout.encodeKObjectInString()))
                },
                onNavigateToAddWorkout = { navController.navigate(Route.WorkoutForm("")) },
                onSignOutUser = { authNavController.navigate(AuthRoute.SignIn) }
            )
        }

        commonNavComposable<Route.Workout> {
            val workoutParam = if(workout.isBlank()) null else workout.decodeStringInKObject<Workout>()

            workoutParam?.let {
                WorkoutPage(
                    workout = workoutParam,
                    workoutViewModel = workoutViewModel,
                    onEditWorkout = { workout ->
                        navController.navigate(Route.WorkoutForm(workout.encodeKObjectInString()))
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        commonNavComposable<Route.WorkoutForm> {
            val workoutParam = if(workout.isBlank()) null else workout.decodeStringInKObject<Workout>()

            WorkoutFormPage(
                workout = workoutParam,
                workoutViewModel = workoutViewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}