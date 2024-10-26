package com.app.mvvm.repo

import com.app.mvvm.model.LoginRequest
import com.app.mvvm.model.LoginResponse
import com.app.mvvm.model.NotificationResponse
import com.app.mvvm.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun login(email: String, password: String, udid: String, callback: (LoginResponse?) -> Unit) {
        val loginRequest = LoginRequest(email, password, udid)

        ApiClient.apiService.callLogin(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun notification(callback: (NotificationResponse?) -> Unit) {
        ApiClient.apiService.calNotification().enqueue(object : Callback<NotificationResponse> {
            override fun onResponse(call: Call<NotificationResponse>, response: Response<NotificationResponse>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}