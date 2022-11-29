package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ProgramReportDetailsFragmentBinding
import java.util.*

private const val TAG = "ProgramsReportFragmentTag"

/*
* ProgramsReportDetailsFragment
* */
class ProgramsReportDetailsFragment : Fragment(R.layout.program_report_details_fragment) {

    private var binding: ProgramReportDetailsFragmentBinding? = null
    private var programReportData: ProgramReportData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProgramReportDetailsFragmentBinding.bind(view)

        val data = ArrayList<ProgramReportData>()

        for (i in 0..IMAGE_COUNT) {
            programReportData = ProgramReportData(
                id = i,
                title = "Google Africa Scholarships 2022 - $i",
                fromDate = GregorianCalendar(2022, 11, 11).toString(),
                toDate = GregorianCalendar(2022, 11, 30).toString(),
                byUser = "David M. N."
            )
            data.add(programReportData!!)
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        with(binding) {
            this!!.title.text = programReportData?.title ?: "NULL"
            details.text = programReportData?.title ?: "NULL"
        }
    }

    private fun goBack() {
        TODO("Not yet implemented")
    }

    private fun searchProgramReports() {
        TODO("Not yet implemented")
    }
}
