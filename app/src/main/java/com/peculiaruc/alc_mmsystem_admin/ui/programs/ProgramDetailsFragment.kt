package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentProgramDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment


class ProgramDetailsFragment : BaseFragment<FragmentProgramDetailsBinding>() {
    private val TAG = "ProgramDetailsTag"

    override val layoutIdFragment: Int = R.layout.fragment_program_details
    override val viewModel: ProgramDetailsViewModel by viewModels()

    private val ADMIN_ID_ARGUMENT = "programID"
    private var programID = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.program_details_title))
        arguments?.let {
            programID = it.getInt(ADMIN_ID_ARGUMENT)
        }

        binding.cardButtonTask.setOnClickListener() {
            val action =
                ProgramDetailsFragmentDirections.actionProgramDetailsFragmentToTaskReportsFragment()
            view.findNavController().navigate(action)
        }

        binding.cardButtonReport.setOnClickListener() {
            val action =
                ProgramDetailsFragmentDirections.actionProgramDetailsFragmentToReportsFragment()
            view.findNavController().navigate(action)
        }
    }
}