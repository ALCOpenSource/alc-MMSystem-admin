package com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.profileMentorMangerRequested

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentApprovalRequestsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.approvalRequests.ApprovalRequestsViewModel
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment


class MentorMangerRequestedFragment  : BaseFragment<FragmentApprovalRequestsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_mentor_manger_requested
    override val viewModel: MentorMangerRequestedViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.approval_requests))
        setBottomNavigationVisibility(false)

    }

}