package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * Task reports fragment
 *
 * @constructor Create empty Task reports fragment
 */
class TaskReportsFragment : BaseFragment<FragmentTaskReportsBinding>(),
    TaskReportsAdapter.ItemClickListener {
    override val layoutIdFragment: Int = R.layout.fragment_task_reports
    override val viewModel = TaskReportsViewModel.getInstance()
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

        //setup recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskReportsAdapter = TaskReportsAdapter(this)
        recyclerView.adapter = taskReportsAdapter
        viewModel.taskReports.observe(viewLifecycleOwner) {
            taskReportsAdapter.submitList(it)
        }
        viewModel.filteredTaskReports.observe(viewLifecycleOwner) {
            taskReportsAdapter.submitList(it)
        }

        //setup spinner
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


    /**
     * On report item click
     *
     * @param item
     */
    override fun onReportItemClick(item: Report) {
        val action =
            TaskReportsFragmentDirections.actionTaskReportsFragmentToTaskReportDetailFragment(1)
        view?.findNavController()?.navigate(action)

    }

    /**
     * On report item download icon click
     *
     * @param item
     */
    override fun onDownloadClick(item: Report) {
        openDownloadDialog()
    }

    /**
     * On report item share icon click
     *
     * @param item
     */
    override fun onShareClick(item: Report) {
        openShareDialog()
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
                viewModel.taskReports.value?.let {
                    filter(newText, it)
                }
                return true
            }
        })

    }

    /**
     * Filter reports from searchview text
     *
     * @param text
     */
    fun filter(text: String?, list: List<Report>) {
        var filteredlist: ArrayList<Report> = ArrayList()
        val length = text?.length ?: 0
        if (length > 0) {

            for (item in list) {
                if (item.title.lowercase().contains(text?.lowercase()!!)) {
                    filteredlist.add(item)
                }

            }

        } else {
            filteredlist = viewModel.taskReports.value as ArrayList<Report>
        }

        viewModel.filteredTaskReports.value = filteredlist
    }

    private fun openDownloadDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog_success)
        val doneButton = dialog.findViewById<Button>(R.id.downloadDoneButton)
        val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
        successTitle?.text = getString(R.string.report_download_dialog_success_message)
        val downloadImage = dialog.findViewById<ImageView>(R.id.success_image)
        downloadImage?.setImageResource(R.drawable.download_dialog_backgroun_1)
        doneButton?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }


    private fun openShareDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog_share)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        val emailButton = dialog.findViewById<Button>(R.id.shareEmailButton)
        emailButton?.setOnClickListener {
            sendEmail()
            dialog.dismiss()
        }
        cancelButton?.setOnClickListener { dialog.dismiss() }
        dialog.setCancelable(true)
        dialog.show()
    }

    /**
     * Send email
     *
     */
    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_SUBJECT, "")
        }
        try {
            startActivity(Intent.createChooser(intent, null))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(),getString(R.string.no_email_app_available),Toast.LENGTH_SHORT).show()
        }
    }
}