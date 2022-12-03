package com.peculiaruc.alc_mmsystem_admin.ui.mentor.notUsed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peculiaruc.alc_mmsystem_admin.databinding.DialogMentorAddedBinding

/**
 * Fragment for mentor added dialog message
 */


class MentorAddedDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogMentorAddedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogMentorAddedBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonDone.setOnClickListener {
//                findNavController().navigate(MentorAddedDialogDirections.actionMentorAddedDialogToMentorFragment())
            }
        }
    }
}