package com.app.mvvm.model

data class LoginRequest(
    val email: String,
    val password: String,
    val UDID: String
)

data class LoginResponse(
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    val data: UserData?
)

data class UserData(
    val accessToken: String,
    val userId: String,
    val email: String,
    val name: String,
    val profilePic: String,
    val isQuestionDisable: Boolean,
    val moodDate: String
)