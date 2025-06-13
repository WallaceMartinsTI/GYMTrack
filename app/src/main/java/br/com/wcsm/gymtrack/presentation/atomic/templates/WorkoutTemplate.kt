package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.domain.model.Exercise
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.atomic.atoms.BackIconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.molecules.PrimaryButtonType
import br.com.wcsm.gymtrack.presentation.atomic.organisms.ExerciseOrganism
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryContainerColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun WorkoutTemplate(
    isLoading: Boolean,
    workout: Workout,
    workoutExercises: List<Exercise>,
    onEditWorkout: (Workout) -> Unit,
    onBackClick: UnitCallback
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BackIconAtom(
                onBack = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            TextAtom(
                text = workout.title,
                color = PrimaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextAtom(
                text = workout.description,
                color = OnBackgroundColor,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            TextAtom(
                text = "Treinos: 7", // Workout counter
                color = PrimaryContainerColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = workoutExercises,
                    key = { it.id }
                ) { exercise ->
                    ExerciseOrganism(
                        isLoading = isLoading,
                        exercise = exercise
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            FilledPrimaryButtonMolecule(onClick = {}) {
                TextAtom(text = "ADICIONAR EXERCÍCIO")
            }

            FilledPrimaryButtonMolecule(
                onClick = { onEditWorkout(workout) },
                buttonType = PrimaryButtonType.WARNING
            ) {
                TextAtom(
                    text = "ATUALIZAR TREINO"
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutTemplatePreview() {
    val workout = Workout(
        id = "1",
        title = "Treino de Pernas I",
        description = "Descrição do treino de pernas I"
    )

    val exercise = Exercise(
        id = "1",
        workoutId = "1",
        title = "Treino de Pernas Básico",
        notes = "Agachamento 4 séries de 30 repetições",
        imageUrl = ""
    )
    
    val workoutExercises = listOf(
        exercise,
        exercise.copy(id = "2", "1", "Treino 2", "Descrição Treino 2"),
        exercise.copy(id = "3", "1", "Treino 3", "Descrição Treino 3")
    )
    WorkoutTemplate(
        isLoading = false,
        workout = workout,
        workoutExercises = workoutExercises,
        onEditWorkout = {},
        onBackClick = {}
    )
}