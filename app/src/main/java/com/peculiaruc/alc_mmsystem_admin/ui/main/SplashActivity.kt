package com.peculiaruc.alc_mmsystem_admin.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.ui.authentication.AuthenticationActivity

/**
 * this is the screen that hosts the splash screen interaction
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}