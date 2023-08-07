package com.example.ui_example

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.ui_example.databinding.ActivityLoginBinding

@SuppressLint("SetTextI18n")

    class LoginActivity : AppCompatActivity(), View.OnClickListener {

        private lateinit var loginButton: Button
        private lateinit var eTUsername: EditText
        private lateinit var eTPassword: EditText

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            loginButton = findViewById(R.id.btnLogin)
            eTUsername = findViewById(R.id.eTUsername)
            eTPassword = findViewById(R.id.eTPassword)

            loginButton.setOnClickListener(this)
        }

    override fun onClick(v: View?) {
        when(v?.id) {
            loginButton.id -> {
                if (eTUsername.text.isNullOrEmpty()) {
                    eTUsername.error = "Username is empty"
                } else if (eTPassword.text.isNullOrEmpty()) {
                    eTPassword.error = "Password is empty"
                } else {
                    Log.d("LoginActivity", "Username: ${eTUsername.text}, Password: ${eTPassword.text}")
                }
            }
        }
    }
}

