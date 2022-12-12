package com.quebuu.mvvm_compose_android.features.splash.view

import com.quebuu.mvvm_compose_android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.quebuu.mvvm_compose_android.BuildConfig.VERSION_NAME
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography
import com.quebuu.mvvm_compose_android.features.home.navigation.FeatureHomeNavigation
import com.quebuu.mvvm_compose_android.features.splash.state.FeatureSplashState
import com.quebuu.mvvm_compose_android.features.splash.viewmodel.FeatureSplashViewModel
import com.quebuu.mvvm_compose_android.features.welcome.navigation.FeatureWelcomeNavigation

@Composable
fun FeatureSplashScreenRoute(
    navigateToWelcomeScreen: () -> Unit = {},
    navigateToHomeScreen: () -> Unit = {},
    viewModel: FeatureSplashViewModel = hiltViewModel(),
) {

    /*
    * View
    * */
    featureSplashScreen()

    /*
    * Observer
    * */
    val splashState = viewModel.destinationFlow.collectAsState(null)

    LaunchedEffect(splashState.value) {
        (splashState.value as? FeatureSplashState.Success)?.let {
            when (it.destination) {
                FeatureWelcomeNavigation.destination -> navigateToWelcomeScreen()
                FeatureHomeNavigation.destination -> navigateToHomeScreen()
            }
        }
    }

    /*
    * Start
    * */
    LaunchedEffect(Unit) {
        viewModel.checkSession()
    }

}

@Composable
@Preview
fun featureSplashScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (tvVersion, ivLogo, tvCopyRight) = createRefs()
        Text(
            modifier = Modifier.constrainAs(tvVersion) {
                top.linkTo(parent.top, 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = VERSION_NAME,
            style = QuebuuTypography.body2
        )
        Image(
            modifier = Modifier.constrainAs(ivLogo) {
                top.linkTo(anchor = parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            painter = painterResource(id = R.drawable.feature_splash_ic_logo),
            contentDescription = stringResource(id = R.string.feature_splash_logo_content),
            alignment = Alignment.Center,
            contentScale = ContentScale.None
        )
        Text(
            modifier = Modifier
                .constrainAs(tvCopyRight) {
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    bottom.linkTo(parent.bottom, 24.dp)
                },
            text = stringResource(id = R.string.feature_splash_copyright),
            style = QuebuuTypography.body2,
            textAlign = TextAlign.Center
        )
    }
}