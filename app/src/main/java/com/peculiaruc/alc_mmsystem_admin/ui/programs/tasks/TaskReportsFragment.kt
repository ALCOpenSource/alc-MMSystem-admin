package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.ProgramsFragmentDirections

class TaskReportsFragment : BaseFragment<FragmentTaskReportsBinding>(),
    TaskReportsAdapter.ItemClickListener {
    val TAG = "TaskReportsFragmentTag"
    override val layoutIdFragment: Int = R.layout.fragment_task_reports
    override val viewModel: TaskReportsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var taskReportsAdapter: TaskReportsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.task_reports_title))
        setHasOptionsMenu(true)

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
        viewModel.filteredTaskReports.observe(viewLifecycleOwner) {
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
        val action =
            TaskReportsFragmentDirections.actionTaskReportsFragmentToTaskReportDetailFragment(1)
        view?.findNavController()?.navigate(action)

    }

    override fun onDownloadClick(item: Report) {
        showDownloadDialog()
    }

    override fun onShareClick(item: Report) {
        showShareDialog()
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
                filter(newText)
                return true
            }
        })

    }

    private fun filter(text: String?) {
        var filteredlist: ArrayList<Report> = ArrayList()
        val length = text?.length ?: 0
        if (length > 0) {
            viewModel.taskReports.value?.let {
                for (item in it) {
                    if (item.title.lowercase().contains(text?.lowercase()!!)) {
                        filteredlist.add(item)
                    }
                }
            }

        } else {
            filteredlist = viewModel.taskReports.value as ArrayList<Report>
        }

        viewModel.filteredTaskReports.value = filteredlist
    }

    private fun showDownloadDialog() {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_download)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val doneButton = dialog.findViewById(R.id.downloadDoneButton) as Button
            val downloadImage = dialog.findViewById(R.id.downloadImage) as ImageView
            downloadImage.setImageResource(R.drawable.download_dialog_backgroun_1)
            doneButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    private fun showShareDialog() {
        activity?.let {

            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_share)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val cancelButton = dialog.findViewById(R.id.cancelButton) as Button
            val emailButton = dialog.findViewById(R.id.shareEmailButton) as Button
            emailButton.setOnClickListener {
                sendEmail()
                dialog.dismiss()
            }
            cancelButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_SUBJECT, "")
        }
        try {
            startActivity(Intent.createChooser(intent, null))
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, e.toString())
        }
    }
}