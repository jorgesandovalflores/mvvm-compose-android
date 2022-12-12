package com.quebuu.mvvm_compose_android.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.quebuu.mvvm_compose_android.core.presentation.navigation.QuebuuNavDestination
import com.quebuu.mvvm_compose_android.features.login.view.FeatureLoginScreenRoute

object FeatureLoginNavigation : QuebuuNavDestination {
    override val route = "login_route"
    override val destination = "login_destination"
}
fun NavGraphBuilder.loginGraph(
    onBackClick: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = FeatureLoginNavigation.route,
        startDestination = FeatureLoginNavigation.destination
    ) {
        composable(FeatureLoginNavigation.destination) {
            FeatureLoginScreenRoute(
                onBackClick = onBackClick
            )
        }
        nestedGraphs()
    }
}