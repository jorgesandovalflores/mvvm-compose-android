package com.quebuu.mvvm_compose_android.common.domain.util

object Constants {
    object Regex {
        const val EMAIL = "^[A-Za-z0-9+_.-]+@([a-zA-Z0-9+_.-]+)\\.([a-zA-Z]{2,6})$"
        const val PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d=\\[\\]{}|'!@#\$%^&*()_+-]{8,20}$"
    }
}