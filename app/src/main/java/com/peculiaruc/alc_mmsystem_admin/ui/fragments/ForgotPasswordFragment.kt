package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentForgotPasswordBinding

/**
 * Fragment for forgot password screen
 */


class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)

        binding.apply {
            buttonDone.setOnClickListener {
                findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToNewPasswordFragment())
            }
        }

        return binding.root
    }
}