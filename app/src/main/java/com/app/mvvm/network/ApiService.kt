package com.app.mvvm.network

import com.app.mvvm.model.LoginRequest
import com.app.mvvm.model.LoginResponse
import com.app.mvvm.model.NotificationData
import com.app.mvvm.model.NotificationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun callLogin(@Body request: LoginRequest): Call<LoginResponse>

    @GET("post/push-notification-list")
    fun calNotification(): Call<NotificationResponse>
}