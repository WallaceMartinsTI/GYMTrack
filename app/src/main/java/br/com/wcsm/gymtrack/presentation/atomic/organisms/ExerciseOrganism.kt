package br.com.wcsm.gymtrack.presentation.atomic.organisms

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.domain.model.Exercise
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryContainerColor
import br.com.wcsm.gymtrack.presentation.ui.theme.ShimmerColor
import br.com.wcsm.gymtrack.utils.Constants
import coil3.compose.AsyncImage

@Composable
fun ExerciseOrganism(
    isLoading: Boolean,
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    if(isLoading) {
        ExerciseOrganismSkeleton()
    } else {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, PrimaryContainerColor, RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { TODO() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = exercise.imageUrl.ifBlank { Constants.DEFAULT_EXERCISE_IMAGE },
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.workout_image_description),
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                TextAtom(
                    text = exercise.title,
                    color = OnBackgroundColor,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(4.dp))

                TextAtom(
                    text = exercise.notes,
                    color = OnBackgroundColor,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ExerciseOrganismPreview() {
    val exercise = Exercise(
        id = "1",
        workoutId = "1",
        title = "Treino de Pernas Básico",
        notes = "Agachamento 4 séries de 30 repetições",
        imageUrl = ""
    )

    Column {
        ExerciseOrganism(
            isLoading = false,
            exercise = exercise
        )

        Spacer(Modifier.height(16.dp))

        ExerciseOrganism(
            isLoading = true,
            exercise = exercise
        )
    }
}

@Composable
private fun ExerciseOrganismSkeleton(modifier: Modifier = Modifier) {
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
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(skeletonColor)
        )

        Spacer(Modifier.width(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(25.dp)
                    .width(180.dp)
                    .background(skeletonColor)
            )

            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(300.dp)
                    .background(skeletonColor)
            )
        }
    }
}

@Preview
@Composable
private fun ExerciseOrganismSkeletonPreview() {
    ExerciseOrganismSkeleton()
}