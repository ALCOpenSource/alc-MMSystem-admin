package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peculiaruc.alc_mmsystem_admin.databinding.DialogPasswordResetBinding

/**
 * Fragment for password reset dialog
 */


class PasswordResetDialog: BottomSheetDialogFragment() {

    private lateinit var binding: DialogPasswordResetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogPasswordResetBinding.inflate(layoutInflater)

        return binding.root
    }
}