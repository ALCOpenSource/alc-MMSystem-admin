package com.peculiaruc.alc_mmsystem_admin.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentNewPasswordBinding

/**
 * Fragment for new password input screen
 */

class NewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentNewPasswordBinding.inflate(layoutInflater)

        binding.apply {
            buttonResetPassword.setOnClickListener {
                findNavController().navigate(NewPasswordFragmentDirections.actionNewPasswordFragmentToPasswordResetDialog())
            }
        }

        return binding.root
    }

}