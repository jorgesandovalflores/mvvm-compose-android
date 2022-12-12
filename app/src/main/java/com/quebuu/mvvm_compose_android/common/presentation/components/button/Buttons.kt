package com.quebuu.mvvm_compose_android.common.presentation.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quebuu.mvvm_compose_android.R
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography

@Preview
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "Primary Button",
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .defaultMinSize(
                minWidth = dimensionResource(id = R.dimen.component_button_width),
                minHeight = dimensionResource(id = R.dimen.component_button_height)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = when {
                        enabled -> listOf(
                            colorResource(id = R.color.component_button_gradient_enabled_start),
                            colorResource(id = R.color.component_button_gradient_enabled_end)
                        )
                        else -> listOf(
                            colorResource(id = R.color.component_button_gradient_disabled_start),
                            colorResource(id = R.color.component_button_gradient_disabled_end)
                        )
                    }
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.component_button_corner_radius))
            ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = colorResource(id = R.color.component_button_primary_enabled),
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = colorResource(id = R.color.component_button_primary_disabled)
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.component_button_corner_radius)),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = QuebuuTypography.button
        )
    }
}

@Preview
@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "Secondary Button",
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .defaultMinSize(
                minWidth = dimensionResource(id = R.dimen.component_button_width),
                minHeight = dimensionResource(id = R.dimen.component_button_height)
            ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = colorResource(id = R.color.component_button_secondary_enabled),
            disabledBackgroundColor = Color.White,
            disabledContentColor = colorResource(id = R.color.component_button_secondary_disabled)
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.component_button_corner_radius)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.component_button_secondary_border_width),
            color = colorResource(id = R.color.black_08)
        ),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = QuebuuTypography.button
        )
    }
}