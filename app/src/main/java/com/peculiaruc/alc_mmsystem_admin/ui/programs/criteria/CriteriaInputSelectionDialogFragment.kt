package com.peculiaruc.alc_mmsystem_admin.ui.programs.criteria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentCriteriaInputSelectionDialogBinding

class CriteriaInputSelectionDialogFragment: DialogFragment() {
    lateinit var binding : FragmentCriteriaInputSelectionDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCriteriaInputSelectionDialogBinding.inflate(inflater, container, false)

        return binding.root
    }
}