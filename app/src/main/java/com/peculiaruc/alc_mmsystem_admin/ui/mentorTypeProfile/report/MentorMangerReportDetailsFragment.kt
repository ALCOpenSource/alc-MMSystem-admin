package com.peculiaruc.alc_mmsystem_admin.ui.mentorTypeProfile.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentMentorMangerReportDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.dialogs.DialogTypes
import com.peculiaruc.alc_mmsystem_admin.utilities.event.EventObserve

class MentorMangerReportDetailsFragment : BaseFragment<FragmentMentorMangerReportDetailsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_mentor_manger_report_details
    override val viewModel: MentorMangerReportDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        setBottomNavigationVisibility(false)
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.closeReportEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigateUp()
        })

        viewModel.shareReportEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(
                MentorMangerReportDetailsFragmentDirections.actionMentorMangerReportDetailsFragmentToBasicDialog(
                    DialogTypes.SHARE_REPORT
                )
            )
        })

        viewModel.downloadReportEvent.observe(viewLifecycleOwner, EventObserve {
            findNavController().navigate(
                MentorMangerReportDetailsFragmentDirections.actionMentorMangerReportDetailsFragmentToBasicDialog(
                    DialogTypes.REPORT_DOWNLOAD
                )
            )
        })
    }

}
