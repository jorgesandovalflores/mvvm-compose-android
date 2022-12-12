package com.quebuu.mvvm_compose_android.common.presentation.state

sealed class DataState {
    object Valid : DataState()
    object Empty : DataState()
    object Invalid : DataState()
}