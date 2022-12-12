package com.quebuu.mvvm_compose_android.common.presentation.components.textinput

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quebuu.mvvm_compose_android.R
import com.quebuu.mvvm_compose_android.common.presentation.views.QuebuuTypography

@Composable
fun BaseTextInput(
    modifier: Modifier = Modifier,
    label: Int? = null,
    placeholder: Int? = null,
    contentDesc: String? = null,
    textFieldState: TextInputState,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier) {
        label?.let {
            Text(
                text = stringResource(id = it),
                style = QuebuuTypography.body2
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .then(contentDesc?.let { Modifier.semantics { contentDescription = contentDesc } }
                    ?: Modifier),
            value = textFieldState.text,
            onValueChange = { textFieldState.setValue(it.take(textFieldState.maxCharacter)) },
            textStyle = QuebuuTypography.h3,
            placeholder = {
                placeholder?.let {
                    Text(
                        text = stringResource(id = it),
                        style = QuebuuTypography.h3
                    )
                }
            },
            isError = textFieldState.isError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.White,
                disabledBorderColor = colorResource(id = R.color.k_quebuu_100),
                focusedBorderColor = colorResource(id = R.color.k_quebuu_500),
                errorBorderColor = colorResource(id = R.color.red_500),
                cursorColor = colorResource(id = R.color.k_quebuu_500),
                placeholderColor = colorResource(id = R.color.k_quebuu_100)
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.component_textinput_corner_radius)),
            singleLine = singleLine,
            maxLines = maxLines,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            visualTransformation = visualTransformation
        )
    }
}

@Composable
fun EmailTextInput(
    modifier: Modifier = Modifier,
    label: Int = R.string.component_textinput_email,
    placeholder: Int? = null,
    textFieldState: TextInputState,
    imeAction: ImeAction = ImeAction.Next
) {
    Column(modifier = modifier) {
        with(textFieldState) {
            BaseTextInput(
                label = label,
                placeholder = placeholder,
                contentDesc = stringResource(id = label),
                textFieldState = this,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = imeAction
                )
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = when {
                    isError -> stringResource(id = errorMessage)
                    else -> ""
                },
                style = QuebuuTypography.caption.copy(
                    color = colorResource(id = R.color.red_500)
                )
            )
        }
    }
}

@Composable
fun PasswordTextInput(
    modifier: Modifier = Modifier,
    label: Int = R.string.component_textinput_password,
    placeholder: Int? = null,
    textFieldState: TextInputState,
    imeAction: ImeAction = ImeAction.Done
) {
    val showPassword = remember { mutableStateOf(false) }
    val trailingIconContentDesc = stringResource(id = R.string.component_textinput_hide_password_content_desc)
    Column(modifier = modifier) {
        with(textFieldState) {
            BaseTextInput(
                label = label,
                placeholder = placeholder,
                contentDesc = stringResource(id = label),
                textFieldState = this,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = imeAction
                ),
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.semantics {
                            contentDescription = trailingIconContentDesc
                        },
                        onClick = { showPassword.value = !showPassword.value }
                    ) {
                        Icon(
                            imageVector = if (showPassword.value) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (showPassword.value) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = when {
                    isError -> stringResource(id = errorMessage)
                    else -> ""
                },
                style = QuebuuTypography.caption.copy(
                    color = colorResource(id = R.color.red_500)
                )
            )
        }
    }
}

@Preview(name = "LimaTextInput")
@Composable
fun BaseTextInputPreview() {
    BaseTextInput(
        label = R.string.component_textinput_title,
        textFieldState = remember { TextInputState() }
    )
}

@Preview(name = "EmailTextInput")
@Composable
fun EmailTextInputPreview() {
    EmailTextInput(
        textFieldState = remember { TextInputState(maxCharacter = 50) }
    )
}

@Preview(name = "PasswordTextInput")
@Composable
fun PasswordTextInputPreview() {
    PasswordTextInput(
        textFieldState = remember { TextInputState(maxCharacter = 16) }
    )
}