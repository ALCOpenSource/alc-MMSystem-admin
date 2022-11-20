package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

class TaskReportDetailsFragment : BaseFragment<FragmentTaskReportDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_task_report_details
    override val viewModel: TaskReportsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        binding.taskReportCloseButton.setOnClickListener() {
            view.findNavController().popBackStack()
        }
    }
}