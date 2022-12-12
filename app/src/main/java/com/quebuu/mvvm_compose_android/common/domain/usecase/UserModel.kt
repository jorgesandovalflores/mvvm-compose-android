package com.quebuu.mvvm_compose_android.common.domain.usecase

data class UserModel(
    val id: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val token: String
)