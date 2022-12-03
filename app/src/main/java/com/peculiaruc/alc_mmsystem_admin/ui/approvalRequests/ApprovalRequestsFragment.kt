package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentApprovalRequestsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.utilities.event.EventObserve

class ApprovalRequestsFragment : BaseFragment<FragmentApprovalRequestsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_approval_requests
    override val viewModel: ApprovalRequestsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.approval_requests))
        setBottomNavigationVisibility(false)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.mentorMangerRequestEvent.observe(viewLifecycleOwner, EventObserve {

        })

        viewModel.mentorRequestEvent.observe(viewLifecycleOwner, EventObserve {

        })

        viewModel.programRequestEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(ApprovalRequestsFragmentDirections.actionApprovalRequestsFragmentToApprovalProgramRequestsFragment())
        })
    }

}