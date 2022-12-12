package com.quebuu.mvvm_compose_android.common.presentation.components.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.quebuu.mvvm_compose_android.R
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography

@Composable
@Preview
fun BaseTopAppBar(
    title: String = "",
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Box(contentAlignment = Alignment.BottomStart) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = QuebuuTypography.button
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.component_toolbar_ic_back),
                        contentDescription = stringResource(id = R.string.component_toolbar_back_button_content_desc)
                    )
                }
            },
            actions = actions,
            backgroundColor = Color.White,
            elevation = dimensionResource(id = R.dimen.component_toolbar_app_top_bar_elevation)
        )
        Image(
            painter = painterResource(id = R.drawable.component_toolbar_bg_top_bar),
            contentDescription = null,
            alignment = Alignment.BottomStart,
            contentScale = ContentScale.Crop
        )
    }
}