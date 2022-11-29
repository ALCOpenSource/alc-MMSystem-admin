package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ProgramReportFragmentBinding
import java.util.*

private const val TAG = "ProgramsReportFragmentTag"

class ProgramsReportFragment : Fragment(R.layout.program_report_fragment) {

    private var binding: ProgramReportFragmentBinding? = null
    private lateinit var programsReportAdapter: ProgramsReportAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProgramReportFragmentBinding.bind(view)

        setupToolbar()

        setUpSpinner()

        val data = ArrayList<ProgramReportData>()

        for (i in 0..IMAGE_COUNT) {
            val programReportData = ProgramReportData(
                id = i,
                title = "Google Africa Scholarships 2022 - $i",
                fromDate = GregorianCalendar(2022, 11, 11).toString(),
                toDate = GregorianCalendar(2022, 11, 30).toString(),
                byUser = "David M. N."
            )
            data.add(programReportData)
        }

        programsReportAdapter = ProgramsReportAdapter(data)

        with(binding!!) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.hasFixedSize()
            recyclerView.adapter = programsReportAdapter
        }
    }

    private fun setupToolbar() {
        with(binding) {
            this?.toolbar!!.title.text = getString(R.string.program_reports)
            toolbar.button.visibility = View.GONE
            toolbar.back.setOnClickListener { goBack() }
            toolbar.search.setOnClickListener { searchProgramReports() }
        }
    }

    private fun setUpSpinner() {
        context?.let {
            val spinnerAdapter = ArrayAdapter.createFromResource(
                it,
                R.array.reports_string_array,
                android.R.layout.simple_spinner_item
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding!!.filter.spinner.adapter = spinnerAdapter
        }
    }

    private fun goBack() {
        TODO("Not yet implemented")
    }

    private fun searchProgramReports() {
        TODO("Not yet implemented")
    }
}
