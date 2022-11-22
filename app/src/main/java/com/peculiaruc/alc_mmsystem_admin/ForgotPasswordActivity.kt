package com.peculiaruc.alc_mmsystem_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit

class ForgotPasswordActivity : AppCompatActivity(R.layout.activity_forgot_password) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (savedInstanceState == null){
            supportFragmentManager.commit{
                setReorderingAllowed(true)
                add<ForgotPasswordMessageFragment>(R.id.fragment_container_view)


            }
        }
    }
}