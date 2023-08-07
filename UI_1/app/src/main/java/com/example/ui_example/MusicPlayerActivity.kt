package com.example.ui_example

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextWatcher
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.ui_example.databinding.ActivityMusicPlayerBinding
import com.google.android.material.slider.Slider

@SuppressLint("SetTextI18n")
class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)

        with(binding) {
            tvSong.text = "Healing Foot"
            tvArtist.text = "Linkin Park"
        }

        setContentView(binding.root)
    }
}

