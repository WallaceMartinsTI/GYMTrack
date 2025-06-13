package br.com.wcsm.gymtrack.utils

import android.app.Activity
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Activity
fun Activity.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// Encode/Decode Extensions
inline fun <reified T> String?.decodeStringInKObject(): T? {
    return try {
        this?.let {
            val modelArg = Base64.decode(this, Base64.URL_SAFE)
            Json.decodeFromString<T>(String(modelArg))
        }
    } catch (e: Exception) {
        Log.e("ARG DECODE", null, e)
        e.printStackTrace()
        null
    }
}

inline fun <reified T> T.encodeKObjectInString(): String {
    return try {
        Base64.encodeToString(Json.encodeToString(this).toByteArray(), Base64.URL_SAFE)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

// Nav Graph Builder
inline fun <reified T : Any> NavGraphBuilder.commonNavComposable(
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = { this.baseSlideIntoContainer() },
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = { this.baseSlideOutContainer() },
    noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = { this.basePopSlideIntoContainer() },
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = { this.basePopSlideOutContainer() },
    noinline content: @Composable T.(NavBackStackEntry) -> Unit
) = this.composable<T>(
    enterTransition = enterTransition,
    exitTransition = exitTransition,
    popEnterTransition = popEnterTransition,
    popExitTransition = popExitTransition
) { navBackStackEntry ->
    navBackStackEntry.toRoute<T>().content(navBackStackEntry)
}

// Transition Animations
fun AnimatedContentTransitionScope<NavBackStackEntry>.baseSlideIntoContainer(): EnterTransition {
    return this.slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(ComposeAnimConstants.COMPOSE_DEFAULT_ANIM_TIME)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.baseSlideOutContainer(): ExitTransition {
    return this.slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(ComposeAnimConstants.COMPOSE_DEFAULT_ANIM_TIME)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.basePopSlideIntoContainer(): EnterTransition {
    return this.slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(ComposeAnimConstants.COMPOSE_DEFAULT_ANIM_TIME)
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.basePopSlideOutContainer(): ExitTransition {
    return this.slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(ComposeAnimConstants.COMPOSE_DEFAULT_ANIM_TIME)
    )
}

object ComposeAnimConstants {
    const val COMPOSE_DEFAULT_ANIM_TIME = 400
}