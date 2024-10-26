package com.app.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mvvm.model.LoginResponse
import com.app.mvvm.model.NotificationResponse
import com.app.mvvm.model.UserData
import com.app.mvvm.repo.UserRepository

// UserViewModel.kt
class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> get() = _loginResponse

    private val _notificationResponse = MutableLiveData<NotificationResponse?>()
    val notificationResponse: LiveData<NotificationResponse?> get() = _notificationResponse


    fun login(email: String, password: String, udid: String) {
        userRepository.login(email, password, udid) { response ->
            println("login response : ${response?.message}")
            _loginResponse.postValue(response)
        }
    }

    fun notification() {
        userRepository.notification { response ->
            println("notification response : ${response?.message}")
            _notificationResponse.postValue(response)
        }
    }
}
