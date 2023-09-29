package com.example.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intents.databinding.ActivityImplicitBinding

class ImplicitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImplicitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImplicitBinding.inflate(layoutInflater)

        with(binding) {
            btnWeb.setOnClickListener {
                val webIntent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("https://developer.android.com/docs")
                }
                startActivity(webIntent)
            }

            btnDial.setOnClickListener {
                val dialIntent = Intent().apply {
                    action = Intent.ACTION_DIAL
                    data = Uri.parse("tel:123456")
                }
                startActivity(dialIntent)
            }

            btnMail.setOnClickListener {
                val mailIntent = Intent().apply {
                    action = Intent.ACTION_SENDTO
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("dev@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Subject")
                    putExtra(Intent.EXTRA_TEXT, "Message body")
                }
                startActivity(mailIntent)
            }
        }

        setContentView(binding.root)
    }
}