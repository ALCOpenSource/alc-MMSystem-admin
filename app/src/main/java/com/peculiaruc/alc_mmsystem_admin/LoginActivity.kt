package com.peculiaruc.alc_mmsystem_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.peculiaruc.alc_mmsystem_admin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setup view Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //underline sign up text
        val mSpannableString = SpannableString(binding.signUpLink.text)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length,0)
        binding.apply {
            signUpLink.text = mSpannableString
            forgotPasswordLink.setOnClickListener {
                val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
        }





    }
}