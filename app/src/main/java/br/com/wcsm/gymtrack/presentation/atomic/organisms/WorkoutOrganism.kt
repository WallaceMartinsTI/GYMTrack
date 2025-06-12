package br.com.wcsm.gymtrack.presentation.atomic.organisms

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import coil3.compose.AsyncImage

@Composable
fun WorkoutOrganism(
    workout: Workout,
    onWorkoutClick: (Workout) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, PrimaryColor, RoundedCornerShape(16.dp)),
        onClick = { onWorkoutClick(workout) },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = R.drawable.gym_track_logo,
                //model = workout.imageUrl,
                contentDescription = stringResource(R.string.workout_image_description),
                modifier = Modifier.size(60.dp).clip(CircleShape)
            )

            Spacer(Modifier.width(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TextAtom(
                    text = workout.title,
                    color = OnBackgroundColor,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                TextAtom(
                    text = workout.description,
                    color = OnBackgroundColor,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                TextAtom(
                    text = stringResource(
                        R.string.home_workout_organism_exercises_counter_text,
                        workout.exercisesCount
                    ),
                    color = OnBackgroundColor
                )
            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun WorkoutOrganismPreview() {
    val workout = Workout(
        id = "1",
        title = "Título do treino é muito grande vamos ver como que fica",
        description = "Descrição do treino muito grande, mas vou deixar com duas linhas, vamos ver como fica",
        exercisesCount = 0,
        imageUrl = "https://www.mensjournal.com/.image/t_share/MTk2MTM1ODg5Njc5OTUwOTkz/best-chest-exercises-for-2025-barbell-bench-press.jpg"
    )

    Column {
        WorkoutOrganism(
            workout = workout,
            onWorkoutClick = {}
        )

        Spacer(Modifier.height(16.dp))

        WorkoutOrganism(
            workout = workout.copy(title = "Costas I", description = "Treino de costas nível básico"),
            onWorkoutClick = {}
        )
    }
}