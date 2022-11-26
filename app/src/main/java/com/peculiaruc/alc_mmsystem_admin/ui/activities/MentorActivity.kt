package com.peculiaruc.alc_mmsystem_admin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ActivityMentorBinding

class MentorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}