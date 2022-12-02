package com.peculiaruc.alc_mmsystem_admin.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentLoginBinding
import com.peculiaruc.alc_mmsystem_admin.ui.main.MainActivity

/**
 * Fragment for login page
 */


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)
        //underline sign up text
        val mSpannableString = SpannableString(binding.signUpLink.text)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        binding.apply {
            signUpLink.text = mSpannableString
            forgotPasswordLink.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
            }
            buttonLogin.setOnClickListener {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }




        return binding.root
    }
}
