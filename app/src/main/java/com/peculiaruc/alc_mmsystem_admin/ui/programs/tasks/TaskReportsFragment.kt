package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.ui.programs.ProgramDetailsViewModel

class TaskReportsFragment : BaseFragment<FragmentTaskReportsBinding>(),
    TaskReportsAdapter.ItemClickListener {

    override val layoutIdFragment: Int = R.layout.fragment_task_reports
    override val viewModel: TaskReportsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initTaskReports()
        val recyclerView = binding.taskReportsList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val taskReportsAdapter = TaskReportsAdapter(this)
        recyclerView.adapter = taskReportsAdapter
        viewModel.taskReports.observe(viewLifecycleOwner) {
            taskReportsAdapter.submitList(it)
        }
    }

    override fun onReportItemClick(item: Report) {
        TODO("Not yet implemented")
    }

    override fun onDownloadClick(item: Report) {
        TODO("Not yet implemented")
    }

    override fun onShareClick(item: Report) {
        TODO("Not yet implemented")
    }

}