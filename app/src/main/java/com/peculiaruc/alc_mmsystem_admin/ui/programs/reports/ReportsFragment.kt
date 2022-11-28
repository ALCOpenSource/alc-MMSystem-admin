package com.peculiaruc.alc_mmsystem_admin.ui.programs.reports

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
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentReportsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.Report
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment


/**
 * Reports fragment
 *
 * @constructor Create empty Reports fragment
 */
class ReportsFragment : BaseFragment<FragmentReportsBinding>(),
    ReportsAdapter.ItemClickListener {
    val TAG = "ReportsFragmentTag"
    override val layoutIdFragment: Int = R.layout.fragment_reports
    override val viewModel: ReportsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var ReportsAdapter: ReportsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.reports_title))
        setHasOptionsMenu(true)

        viewModel.initReports()
        recyclerView = binding.reportsList
        spinner = binding.reportSpinner
        setUpRecycleView()
        setUpSpinner()
    }

    /**
     * Set up recycle view
     *
     */
    fun setUpRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ReportsAdapter = ReportsAdapter(this)
        recyclerView.adapter = ReportsAdapter
        viewModel.reports.observe(viewLifecycleOwner) {
            ReportsAdapter.submitList(it)
        }
        viewModel.filteredReports.observe(viewLifecycleOwner) {
            ReportsAdapter.submitList(it)
        }
    }

    /**
     * Set up spinner
     *
     */
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

    /**
     * On report item click
     *
     * @param item
     */
    override fun onReportItemClick(item: Report) {
        val action =
            ReportsFragmentDirections.actionReportsFragmentToReportDetailFragment(1)
        view?.findNavController()?.navigate(action)
    }

    /**
     * On report item download icon click
     *
     * @param item
     */
    override fun onDownloadClick(item: Report) {
        showDownloadDialog()
    }

    /**
     * On report item share icon click
     *
     * @param item
     */
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

    /**
     * Filter reports from searchview text
     *
     * @param text
     */
    private fun filter(text: String?) {
        var filteredlist: ArrayList<Report> = ArrayList()
        val length = text?.length ?: 0
        if (length > 0) {
            viewModel.reports.value?.let {
                for (item in it) {
                    if (item.title.lowercase().contains(text?.lowercase()!!)) {
                        filteredlist.add(item)
                    }
                }
            }

        } else {
            filteredlist = viewModel.reports.value as ArrayList<Report>
        }

        viewModel.filteredReports.value = filteredlist
    }

    /**
     * Show download dialog
     *
     */
    private fun showDownloadDialog() {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_success)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val doneButton = dialog.findViewById(R.id.downloadDoneButton) as Button
            val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
            successTitle.text = getString(R.string.report_download_dialog_success_message)
            val downloadImage = dialog.findViewById(R.id.success_image) as ImageView
            downloadImage.setImageResource(R.drawable.download_dialog_backgroun_2)
            doneButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    /**
     * Show share dialog
     *
     */
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
            Log.e(TAG, e.toString())
        }
    }
}