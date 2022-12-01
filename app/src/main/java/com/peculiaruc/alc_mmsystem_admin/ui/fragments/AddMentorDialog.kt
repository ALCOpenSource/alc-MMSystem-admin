package com.peculiaruc.alc_mmsystem_admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peculiaruc.alc_mmsystem_admin.databinding.DialogAddMentorsBinding
import com.peculiaruc.alc_mmsystem_admin.databinding.DialogPasswordResetBinding

/**
 * fragment for the add mentor dialog
 */

class AddMentorDialog:BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddMentorsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogAddMentorsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonSend.setOnClickListener {
                findNavController().navigate(AddMentorDialogDirections.actionAddMentorDialogToMentorAddedDialog())
            }
        }
    }
}