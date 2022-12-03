package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.approvalProgramRequest

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentApprovalProgramRequestsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.utilities.event.EventObserve


class ApprovalProgramRequestsFragment : BaseFragment<FragmentApprovalProgramRequestsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_approval_program_requests
    override val viewModel: ApprovalProgramRequestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.applicant_requests))
        setBottomNavigationVisibility(false)
        setHasOptionsMenu(true)
        binding.rvProgramRows.adapter = ApprovalProgramRequestAdapter(emptyList(), viewModel)
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.selectProgramRequests.observe(viewLifecycleOwner, EventObserve {

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_program_request, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}