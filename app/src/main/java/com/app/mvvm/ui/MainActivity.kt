package com.app.mvvm.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.app.mvvm.R
import com.app.mvvm.adapter.AdapterNotificationList
import com.app.mvvm.databinding.ActivityMainBinding
import com.app.mvvm.model.NotificationData
import com.app.mvvm.network.ApiClient.accessToken
import com.app.mvvm.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    var notificationList: ArrayList<NotificationData> = ArrayList()
    var adpNotificationList: AdapterNotificationList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        observer()
        userViewModel.notification()


    }

    private fun observer() {
        userViewModel.notificationResponse.observe(this) { response ->
            if (response != null && response.status) {
                // Handle successful login
                Log.e("TAG", "observerMessage: ${response.message}")
                Log.e("TAG", "observer: ${response.data?.get(0)?.postId}")

                notificationList=response.data

                adpNotificationList=AdapterNotificationList(notificationList)
                binding.rvNotificationList.adapter=adpNotificationList

            } else {
                Log.e("TAG", "observerCode: ${response?.statusCode ?: "Unknown error"}")
                Log.e("TAG", "observerMessage: ${response?.message ?: "Unknown error"}")
            }
        }
    }
}