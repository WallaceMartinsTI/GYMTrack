package br.com.wcsm.gymtrack.di

import br.com.wcsm.gymtrack.domain.repository.WorkoutRepository
import br.com.wcsm.gymtrack.domain.usecase.workout.DeleteWorkoutUseCase
import br.com.wcsm.gymtrack.domain.usecase.workout.GetWorkoutsUseCase
import br.com.wcsm.gymtrack.domain.usecase.workout.SaveWorkoutUseCase
import br.com.wcsm.gymtrack.domain.usecase.workout.UpdateWorkoutUseCase
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object WorkoutKoinModule : KoinModule {
    override val module = module {
        single { GetWorkoutsUseCase(get<WorkoutRepository>()::getWorkouts) }
        single { SaveWorkoutUseCase(get<WorkoutRepository>()::saveWorkout) }
        single { UpdateWorkoutUseCase(get<WorkoutRepository>()::updateWorkout) }
        single { DeleteWorkoutUseCase(get<WorkoutRepository>()::deleteWorkout) }

        viewModelOf(::WorkoutViewModel)
    }
}