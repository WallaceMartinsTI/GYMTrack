package br.com.wcsm.gymtrack.presentation.pages.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.atomic.molecules.ErrorContainerMolecule
import br.com.wcsm.gymtrack.presentation.atomic.organisms.SignOutDialog
import br.com.wcsm.gymtrack.presentation.atomic.templates.ErrorTemplate
import br.com.wcsm.gymtrack.presentation.atomic.templates.HomeTemplate
import br.com.wcsm.gymtrack.presentation.atomic.templates.LoadingTemplate
import br.com.wcsm.gymtrack.presentation.pages.home.viewmodel.HomeState
import br.com.wcsm.gymtrack.presentation.pages.home.viewmodel.HomeViewModel
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutState
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import br.com.wcsm.gymtrack.utils.showToastMessage
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    workoutViewModel: WorkoutViewModel,
    onNavigateToWorkout: (Workout) -> Unit,
    onNavigateToAddWorkout: UnitCallback,
    onSignOutUser: UnitCallback
) {
    val context = LocalContext.current

    val homeViewModel: HomeViewModel = koinViewModel()

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val workoutUiState by workoutViewModel.uiState.collectAsStateWithLifecycle()

    var showSignOutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(homeViewModel) {
        homeViewModel.loadPage()

        homeViewModel.state.collectLatest { state ->
            when(state) {
                HomeState.CurrentUserAvailable -> {
                    val currentUserId = uiState.currentUserId
                    currentUserId?.let {
                        workoutViewModel.handleCurrentUser(currentUserId)
                        workoutViewModel.getWorkouts()
                    }
                }
                HomeState.GoToAddWorkout -> onNavigateToAddWorkout()
                HomeState.ShowSignOutDialog -> { showSignOutDialog = true }
                HomeState.SignOutUser -> {
                    workoutViewModel.handleCurrentUser(null)
                    onSignOutUser()
                }
            }
        }
    }

    LaunchedEffect(workoutViewModel) {
        workoutViewModel.state.collectLatest { state ->
            if(state is WorkoutState.ShowToast) {
                (context as? Activity)?.showToastMessage(state.message)
            }
        }
    }

    LaunchedEffect(workoutUiState.actionSuccessMessage) {
        if(!workoutUiState.actionSuccessMessage.isNullOrBlank()) {
            workoutViewModel.showToastMessage(workoutUiState.actionSuccessMessage!!)
        }
    }

    if(showSignOutDialog) {
        SignOutDialog(
            onExitApp = { (context as? Activity)?.finish() },
            onSignOut = { homeViewModel.signOut() },
            onDismiss = { showSignOutDialog = false }
        )
    }
    
    if(uiState.isLoading || workoutUiState.isLoading) {
        LoadingTemplate()
    }

    if(uiState.error.isNullOrBlank().not()) {
        uiState.error?.let {
            ErrorTemplate(
                errorMessage = it,
                onTryAgain = {
                    homeViewModel.resetErrorMessage()
                    homeViewModel.loadPage()
                }
            )
        }
    } else if(workoutUiState.error.isNullOrBlank().not()) {
        workoutUiState.error?.let {
            ErrorTemplate(
                errorMessage = it,
                onTryAgain = {
                    workoutViewModel.resetErrorMessage()
                    workoutViewModel.getWorkouts()
                }
            )
        }
    }

    HomeTemplate(
        isPageLoading = uiState.isLoading,
        workouts = workoutUiState.workouts,
        onWorkoutClick = { workout ->
            workoutViewModel.setSelectedWorkout(workout)
            onNavigateToWorkout(workout)
        },
        onAddWorkoutClick = onNavigateToAddWorkout,
        onSignOutClick = homeViewModel::handleSignOut
    )
}