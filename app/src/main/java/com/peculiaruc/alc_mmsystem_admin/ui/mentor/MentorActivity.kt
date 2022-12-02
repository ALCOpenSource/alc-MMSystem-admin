package com.peculiaruc.alc_mmsystem_admin.ui.mentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ActivityMentorBinding

/**
* this activity hosts the main flow of the application from all
* mentor list to mentor profile
*/

class MentorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mentor)

    }
}