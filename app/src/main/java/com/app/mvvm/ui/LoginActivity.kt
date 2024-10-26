package com.app.mvvm.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.app.mvvm.R
import com.app.mvvm.databinding.ActivityLoginBinding
import com.app.mvvm.viewmodel.UserViewModel
import androidx.activity.viewModels
import com.app.mvvm.model.LoginResponse
import com.app.mvvm.network.ApiClient.accessToken

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)

        observer()
        binding.btnLogin.setOnClickListener {
            userViewModel.login(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString(),
                "c1d12c4887c6e5d2"
            )
        }
    }

    private fun observer() {
        userViewModel.loginResponse.observe(this) { response ->
            if (response != null && response.status) {
                // Handle successful login
                Log.e("TAG", "observerMessage: ${response.message}")
                Log.e("TAG", "observer: ${response.data?.name}")
                accessToken = response.data?.accessToken ?: ""
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(mainIntent)

            } else {
                // Handle login failure
                Log.e("TAG", "observerCode: ${response?.statusCode ?: "Unknown error"}")
                Log.e("TAG", "observerMessage: ${response?.message ?: "Unknown error"}")
            }
        }
    }

}