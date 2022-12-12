package com.quebuu.mvvm_compose_android.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.quebuu.mvvm_compose_android.features.login.navigation.FeatureLoginNavigation
import com.quebuu.mvvm_compose_android.features.login.navigation.loginGraph

import com.quebuu.mvvm_compose_android.features.splash.navigation.FeatureSplashNavigation
import com.quebuu.mvvm_compose_android.features.splash.navigation.splashGraph
import com.quebuu.mvvm_compose_android.features.welcome.navigation.FeatureWelcomeNavigation
import com.quebuu.mvvm_compose_android.features.welcome.navigation.welcomeGraph

@Composable
fun QuebuuNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = FeatureSplashNavigation.route
) {
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        splashGraph(
            navigateToWelcomeScreen = {
                navController.navigate(FeatureWelcomeNavigation.destination) {
                    popUpTo(FeatureSplashNavigation.destination) { inclusive = true }
                }
            },
            navigateToHomeScreen = {

            },
            nestedGraphs = {
                welcomeGraph(
                    navigateToLoginScreen = {
                        navController.navigate(FeatureLoginNavigation.destination) {

                        }
                    },
                    navigateToRegisterScreen = {

                    },
                    nestedGraphs = {
                        loginGraph(
                            onBackClick = {
                                navController.popBackStack()
                            }, nestedGraphs = {

                            }
                        )
                    }
                )
            }
        )
    }
}