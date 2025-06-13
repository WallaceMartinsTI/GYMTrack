package br.com.wcsm.gymtrack.presentation.atomic.organisms

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
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
import br.com.wcsm.gymtrack.presentation.ui.theme.ShimmerColor
import br.com.wcsm.gymtrack.utils.Constants
import coil3.compose.AsyncImage

@Composable
fun WorkoutOrganism(
    isLoading: Boolean,
    workout: Workout,
    onWorkoutClick: (Workout) -> Unit,
    modifier: Modifier = Modifier
) {
    if(isLoading) {
        WorkoutOrganismSkeleton()
    } else {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, PrimaryColor, RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onWorkoutClick(workout) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(8.dp))

            AsyncImage(
                model = workout.imageUrl.ifBlank { Constants.DEFAULT_WORKOUT_IMAGE },
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.workout_image_description),
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(16.dp))

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
            isLoading = false,
            workout = workout,
            onWorkoutClick = {}
        )

        Spacer(Modifier.height(16.dp))

        WorkoutOrganism(
            isLoading = true,
            workout = workout.copy(
                id = "2",
                title = "Costas II",
                description = "Treino de costas nível intermediário"
            ),
            onWorkoutClick = {}
        )

        Spacer(Modifier.height(16.dp))

        WorkoutOrganism(
            isLoading = false,
            workout = workout.copy(
                id = "3",
                title = "Costas I",
                description = "Treino de costas nível básico"
            ),
            onWorkoutClick = {}
        )
    }
}

@Composable
private fun WorkoutOrganismSkeleton(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val localConfiguration = LocalConfiguration.current
    val target = (localConfiguration.screenWidthDp * 4).toFloat()

    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = target,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val skeletonColor = Brush.linearGradient(
        colors = ShimmerColor,
        end = Offset(x = scale, y = scale)
    )
    
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(skeletonColor)
        )

        Spacer(Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(25.dp)
                    .width(250.dp)
                    .background(skeletonColor)
            )

            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(300.dp)
                    .background(skeletonColor)
            )

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(150.dp)
                    .background(skeletonColor)
            )
        }
    }
}

@Preview
@Composable
private fun WorkoutOrganismSkeletonPreview() {
    WorkoutOrganismSkeleton()
}