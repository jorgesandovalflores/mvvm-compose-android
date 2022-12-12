package com.quebuu.mvvm_compose_android.features.welcome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.quebuu.mvvm_compose_android.core.presentation.navigation.QuebuuNavDestination
import com.quebuu.mvvm_compose_android.features.welcome.view.FeatureWelcomeScreen

object FeatureWelcomeNavigation : QuebuuNavDestination {
    override val route = "welcome_route"
    override val destination = "welcome_destination"
}

fun NavGraphBuilder.welcomeGraph(
    navigateToRegisterScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = FeatureWelcomeNavigation.route,
        startDestination = FeatureWelcomeNavigation.destination
    ) {
        composable(FeatureWelcomeNavigation.destination) {
            FeatureWelcomeScreen(
                navigateToRegisterScreen = navigateToRegisterScreen,
                navigateToLoginScreen = navigateToLoginScreen
            )
        }
        nestedGraphs()
    }
}