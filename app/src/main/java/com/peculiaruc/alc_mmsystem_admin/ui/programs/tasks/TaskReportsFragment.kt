package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

class TaskReportsFragment : BaseFragment<FragmentTaskReportsBinding>(),
    TaskReportsAdapter.ItemClickListener {

    override val layoutIdFragment: Int = R.layout.fragment_task_reports
    override val viewModel: TaskReportsViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var taskReportsAdapter:TaskReportsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.task_reports_title))
        viewModel.initTaskReports()
        recyclerView = binding.taskReportsList
        spinner = binding.taskSpinner
        setUpRecycleView()
        setUpSpinner()
    }

    fun setUpRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskReportsAdapter = TaskReportsAdapter(this)
        recyclerView.adapter = taskReportsAdapter
        viewModel.taskReports.observe(viewLifecycleOwner) {
            taskReportsAdapter.submitList(it)
        }
    }

    fun setUpSpinner() {
        context?.let {
            val spinnerAdapter = ArrayAdapter.createFromResource(
                it,
                R.array.reports_string_array,
                android.R.layout.simple_spinner_item
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdapter

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_reports_search, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                //taskReportsAdapter.filter.filter(newText)
                return true
            }
        })

    }

}