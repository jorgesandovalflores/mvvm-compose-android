package com.quebuu.mvvm_compose_android.features.login.view

import com.quebuu.mvvm_compose_android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography
import com.quebuu.mvvm_compose_android.common.presentation.components.progress.ProgressPopup
import com.quebuu.mvvm_compose_android.common.presentation.components.button.PrimaryButton
import com.quebuu.mvvm_compose_android.common.presentation.components.textinput.EmailTextInput
import com.quebuu.mvvm_compose_android.common.presentation.components.textinput.PasswordTextInput
import com.quebuu.mvvm_compose_android.common.presentation.components.textinput.TextInputState
import com.quebuu.mvvm_compose_android.common.presentation.components.toolbar.BaseTopAppBar
import com.quebuu.mvvm_compose_android.common.presentation.state.FieldState
import com.quebuu.mvvm_compose_android.features.login.state.FeatureLoginState
import com.quebuu.mvvm_compose_android.features.login.viewmodel.FeatureLoginViewModel

@Composable
fun FeatureLoginScreenRoute(
    onBackClick: () -> Unit = {},
    viewModel: FeatureLoginViewModel = hiltViewModel(),
) {

    /*
    * View
    * */

    val emailState = remember {
        TextInputState(maxCharacter = 70)
    }
    val passwordState = remember {
        TextInputState(maxCharacter = 20)
    }

    featureLoginScreen(
        emailState = emailState,
        passwordState = passwordState,
        onBackClick = onBackClick,
        login = viewModel::login
    )

    /*
    * States
    * */

    viewModel.loadingFlow.collectAsState(false).let {
        ProgressPopup(it)
    }

    viewModel.emailState.collectAsState(FieldState.Valid).let {
        when (val response = it.value) {
            FieldState.Valid -> emailState.clearError()
            is FieldState.Invalid -> emailState.setErrorMessage(response.messageResource)
        }
    }

    viewModel.passwordState.collectAsState(FieldState.Valid).let {
        when (val response = it.value) {
            FieldState.Valid -> passwordState.clearError()
            is FieldState.Invalid -> passwordState.setErrorMessage(response.messageResource)
        }
    }

    val loginState = viewModel.destinationFlow.collectAsState("")
    val context = LocalContext.current

    LaunchedEffect(loginState.value) {
        when (val response = loginState.value) {
            FeatureLoginState.Loading -> {
                emailState.clearError()
                passwordState.clearError()
            }
            FeatureLoginState.Success -> {

            }
            is FeatureLoginState.Error -> {

            }
        }
    }

}

@Composable
@Preview
fun featureLoginScreen(
    emailState: TextInputState = TextInputState(),
    passwordState: TextInputState = TextInputState(),
    onBackClick: () -> Unit = {},
    login: (String, String) -> Unit = {_, _ -> }
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = R.string.feature_login_toolbar),
                onBackClick = onBackClick
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.padding(
                        top = 44.dp,
                        bottom = 32.dp
                    ),
                    painter = painterResource(id = R.drawable.feature_login_ic_logo),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = stringResource(R.string.feature_login_title),
                    style = QuebuuTypography.h1
                )

                EmailTextInput(
                    modifier = Modifier.padding(bottom = 22.dp),
                    placeholder = R.string.feature_login_email_placeholder,
                    textFieldState = emailState
                )

                PasswordTextInput(
                    textFieldState = passwordState,
                    placeholder = R.string.feature_login_password_placeholder
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryButton(
                    modifier = Modifier.padding(
                        bottom = 40.dp
                    ).fillMaxWidth(),
                    text = stringResource(R.string.feature_login_button),
                    onClick = { login(emailState.text, passwordState.text) }
                )
            }
        }
    )
}