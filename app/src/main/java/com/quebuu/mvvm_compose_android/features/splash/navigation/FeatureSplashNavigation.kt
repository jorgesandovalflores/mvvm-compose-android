package com.quebuu.mvvm_compose_android.features.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.quebuu.mvvm_compose_android.core.presentation.navigation.QuebuuNavDestination
import com.quebuu.mvvm_compose_android.features.splash.view.FeatureSplashScreenRoute

object FeatureSplashNavigation : QuebuuNavDestination {
    override val route = "splash_route"
    override val destination = "splash_destination"
}

fun NavGraphBuilder.splashGraph(
    navigateToWelcomeScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = FeatureSplashNavigation.route,
        startDestination = FeatureSplashNavigation.destination
    ) {
        composable(FeatureSplashNavigation.destination) {
            FeatureSplashScreenRoute(
                navigateToWelcomeScreen = navigateToWelcomeScreen,
                navigateToHomeScreen = navigateToHomeScreen
            )
        }
        nestedGraphs()
    }
}