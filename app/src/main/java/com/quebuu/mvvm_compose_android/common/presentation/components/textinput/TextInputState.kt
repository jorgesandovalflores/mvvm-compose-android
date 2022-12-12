package com.quebuu.mvvm_compose_android.common.presentation.components.textinput

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TextInputState(val maxCharacter: Int = 50) {

    var text: String by mutableStateOf("")
        private set

    var isError: Boolean = false
        private set

    var errorMessage: Int = -1
        private set

    fun setValue(value: String) {
        this.text = value
    }

    fun clearError() {
        this.isError = false
    }

    fun setErrorMessage(@StringRes message: Int) {
        this.isError = true
        this.errorMessage = message
    }
}