package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import androidx.fragment.app.viewModels
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

class TaskReportDetailsFragment : BaseFragment<FragmentTaskReportDetailsBinding>(){
    override val layoutIdFragment: Int = R.layout.fragment_task_report_details
    override val viewModel: TaskReportsViewModel by viewModels()
}