package br.com.wcsm.gymtrack.presentation.atomic.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.atomic.atoms.FloatingActionButtonAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.IconAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.StylizedTextAtom
import br.com.wcsm.gymtrack.presentation.atomic.atoms.TextAtom
import br.com.wcsm.gymtrack.presentation.atomic.molecules.FilledPrimaryButtonMolecule
import br.com.wcsm.gymtrack.presentation.atomic.organisms.WorkoutOrganism
import br.com.wcsm.gymtrack.presentation.ui.theme.ErrorColor
import br.com.wcsm.gymtrack.presentation.ui.theme.OnBackgroundColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryColor
import br.com.wcsm.gymtrack.presentation.ui.theme.PrimaryContainerColor
import br.com.wcsm.gymtrack.presentation.ui.theme.WhiteColor
import br.com.wcsm.gymtrack.utils.UnitCallback

@Composable
fun HomeTemplate(
    isPageLoading: Boolean,
    workouts: List<Workout>,
    onWorkoutClick: (Workout) -> Unit,
    onAddWorkoutClick: UnitCallback,
    onSignOutClick: UnitCallback
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        HomeHeader(
            userName = "Wallace",
            onSignOutClick = onSignOutClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if(workouts.isEmpty() && !isPageLoading) {
                EmptyWorkoutsContainer(onAddWorkoutClick = onAddWorkoutClick)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        TextAtom(
                            text = "Treinos",
                            color = PrimaryColor,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    items(
                       items = workouts,
                        key = { it.id }
                    ) { workout ->
                        WorkoutOrganism(
                            isLoading = isPageLoading,
                            workout = workout,
                            onWorkoutClick = onWorkoutClick
                        )
                    }

                    item { Spacer(modifier = Modifier.height(60.dp)) }
                }

                if(!isPageLoading) {
                    FloatingActionButtonAtom(
                        icon = Icons.Default.Add,
                        iconDescription = stringResource(R.string.add_icon_description),
                        onClick = onAddWorkoutClick,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun HomeTemplatePreview() {
    val workout = Workout(
        id = "1",
        title = "Costas I",
        description = "Treino de costas nível básico",
        exercisesCount = 0,
        imageUrl = "https://www.mensjournal.com/.image/t_share/MTk2MTM1ODg5Njc5OTUwOTkz/best-chest-exercises-for-2025-barbell-bench-press.jpg"
    )

    val workouts = listOf(
        workout,
        workout.copy(id = "2", title = "Pernas I", exercisesCount = 3),
        workout.copy(id = "3", title = "Bicepts II", exercisesCount = 12)
    )

    HomeTemplate(
        isPageLoading = false,
        workouts = workouts,
        onWorkoutClick = {},
        onAddWorkoutClick = {},
        onSignOutClick = {}
    )
}

@Composable
private fun HomeHeader(
    userName: String,
    onSignOutClick: UnitCallback,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StylizedTextAtom(
            initialText = stringResource(R.string.home_welcome_message),
            textToStyle = " $userName",
            endText = "!",
            color = OnBackgroundColor,
            style = SpanStyle(
                color = PrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        )

        IconAtom(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            iconDescription = stringResource(R.string.logout_icon_description),
            tint = ErrorColor,
            modifier = Modifier.clickable { onSignOutClick() }
        )
    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    HomeHeader(userName = "Wallace", onSignOutClick = {})
}

@Composable
private fun EmptyWorkoutsContainer(
    onAddWorkoutClick: UnitCallback,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconAtom(
            imageVector = Icons.Default.NotInterested,
            iconDescription = stringResource(R.string.not_interested_icon_description),
            tint = PrimaryContainerColor,
            modifier = Modifier.size(42.dp)
        )

        TextAtom(
            text = stringResource(R.string.home_empty_workouts_message),
            color = PrimaryContainerColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        FilledPrimaryButtonMolecule(onClick = onAddWorkoutClick) {
            TextAtom(
                text = stringResource(R.string.add_text).uppercase(),
                style = MaterialTheme.typography.labelMedium
            )

            IconAtom(
                imageVector = Icons.Default.Add,
                iconDescription = stringResource(R.string.add_icon_description),
                tint = WhiteColor
            )
        }
    }
}

@Preview
@Composable
private fun EmptyWorkoutsContainerPreview() {
    EmptyWorkoutsContainer(onAddWorkoutClick = {})
}