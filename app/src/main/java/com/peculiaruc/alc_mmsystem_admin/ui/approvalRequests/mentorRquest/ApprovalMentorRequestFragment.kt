package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.mentorRquest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentApprovalMentorRequestBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.utilities.event.EventObserve


class ApprovalMentorRequestFragment : BaseFragment<FragmentApprovalMentorRequestBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_approval_mentor_request
    override val viewModel: ApprovalMentorRequestProgramViewModel by viewModels()
    private val args: ApprovalMentorRequestFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.select_beneficiary))
        viewModel.setType(args.mentorType)
        binding.recycler.adapter = ApprovalMentorRequestAdapter(emptyList(), viewModel)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.mentorEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(
                ApprovalMentorRequestFragmentDirections
                    .actionApprovalMentorRequestFragmentToProfileRequestedMentorFragment()
            )
        })
    }

}