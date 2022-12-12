package com.quebuu.mvvm_compose_android.features.welcome.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.quebuu.mvvm_compose_android.R
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography
import com.quebuu.mvvm_compose_android.common.presentation.components.button.PrimaryButton
import com.quebuu.mvvm_compose_android.common.presentation.components.button.SecondaryButton

@Composable
@Preview
fun FeatureWelcomeScreen(
    navigateToRegisterScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val (tvTitle, ivLogo, tvDescription, btnGoToRegister, btnGoToLogin) = createRefs()

        Text(
            modifier = Modifier.constrainAs(tvTitle) {
                top.linkTo(parent.top, 89.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(R.string.feature_welcome_title),
            style = QuebuuTypography.h2
        )
        Image(
            modifier = Modifier.constrainAs(ivLogo) {
                top.linkTo(tvTitle.bottom, 68.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            painter = painterResource(id = R.drawable.feature_welcome_ic_logo),
            contentDescription = stringResource(id = R.string.feature_welcome_logo_content_desc)
        )
        Text(
            modifier = Modifier.constrainAs(tvDescription) {
                top.linkTo(ivLogo.bottom, 64.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(R.string.feature_welcome_message),
            textAlign = TextAlign.Center,
            style = QuebuuTypography.body1
        )
        PrimaryButton(
            modifier = Modifier.constrainAs(btnGoToRegister) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(btnGoToLogin.top, 28.dp)
            },
            onClick = navigateToLoginScreen,
            text = stringResource(R.string.feature_welcome_already_registered_button)
        )
        SecondaryButton(
            modifier = Modifier.constrainAs(btnGoToLogin) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 40.dp)
            },
            onClick = navigateToLoginScreen,
            text = stringResource(R.string.feature_welcome_already_registered_button)
        )
    }
}