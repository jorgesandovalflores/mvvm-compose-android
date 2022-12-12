package com.quebuu.mvvm_compose_android.common.presentation.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.quebuu.mvvm_compose_android.R
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LoadingState

fun Flow<*>.mapIsLoadingState() = map { it is LoadingState }

@Composable
fun ProgressPopup(loadingState: State<Boolean>) {
    if (loadingState.value) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.component_progress_popup_size))
                    .background(
                        color = colorResource(id = R.color.y_quebuu_a100),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.component_progress_popup_corner_radius))
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_progress_popup_spacing_top)))
                QuebuuCircularProgressIndicator(loadingState = loadingState)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_progress_popup_spacing_middle)))
                Text(
                    text = stringResource(id = R.string.component_progress_message),
                    style = QuebuuTypography.body1
                )
            }
        }
    }
}

@Composable
fun QuebuuCircularProgressIndicator(
    modifier: Modifier = Modifier,
    loadingState: State<Boolean>
) {
    if (loadingState.value) {
        CircularProgressIndicator(
            modifier = modifier.size(dimensionResource(id = R.dimen.component_progress_indicator_size)),
            color = colorResource(id = R.color.c_quebuu_500),
            strokeWidth = dimensionResource(id = R.dimen.component_progress_indicator_stroke_width)
        )
    } else {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_progress_indicator_size)))
    }
}