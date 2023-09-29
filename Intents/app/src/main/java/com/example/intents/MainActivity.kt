package com.example.intents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intents.databinding.ActivityMainBinding

    const val USER_NAME = "USER_NAME"

    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)

            binding.btnLogin.setOnClickListener {
                val loginIntent = Intent(this, IntentActivity::class.java)
                loginIntent.putExtra(USER_NAME, binding.eTName.text.toString())
                startActivity(loginIntent)
            }

            setContentView(binding.root)
        }
    }


/*
// Open Webpage Url



 */
