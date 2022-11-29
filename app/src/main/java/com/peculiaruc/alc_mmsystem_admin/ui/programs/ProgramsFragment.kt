package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ProgramsFragmentBinding
import java.util.*

private const val TAG = "ProgramsFragmentTag"
const val IMAGE_COUNT = 20

/**
* ProgramsFragment
* */
class ProgramsFragment : Fragment(R.layout.programs_fragment) {

    private var binding: ProgramsFragmentBinding? = null
    private lateinit var programsAdapter: ProgramsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProgramsFragmentBinding.bind(view)

        setupToolbar()

        val data = ArrayList<ProgramData>()

        for (i in 0..IMAGE_COUNT) {
            val programsData = ProgramData(
                id = i,
                image = "https://i.ytimg.com/vi/j9FEFDm-CgQ/maxresdefault.jpg",
                title = "Google Africa Scholarships 2022 - $i",
                date = GregorianCalendar(2022, 11, 11).toString(),
                description = "Some desc",
                isDeleted = false,
                mentorManagers = 10,
                mentors = 20
            )
            data.add(programsData)
        }

        programsAdapter = ProgramsAdapter(data)

        with(binding!!) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.hasFixedSize()
            recyclerView.adapter = programsAdapter
        }
    }

    private fun setupToolbar() {
        with(binding) {
            this?.toolbar!!.title.text = getString(R.string.programs)
            toolbar.button.text = getString(R.string.program_reports)
            toolbar.back.setOnClickListener { goBack() }
            toolbar.search.setOnClickListener { searchPrograms() }
            toolbar.button.setOnClickListener { goToReports() }
            fab.setOnClickListener { createProgram() }
        }
    }

    private fun goToReports() {
        val action = ProgramsFragmentDirections.actionProgramsFragmentToProgramsReportFragment()
        findNavController().navigate(action)
    }

    private fun searchPrograms() {
        with(binding) {
            this?.toolbar!!.search.visibility = View.GONE
            toolbar.title.visibility = View.GONE
            toolbar.button.visibility = View.GONE
            toolbar.searchHere.visibility = View.VISIBLE
        }
    }

    private fun goBack() {
        with(binding) {
            if (this?.toolbar!!.searchHere.visibility == View.VISIBLE) {
                toolbar.search.visibility = View.VISIBLE
                toolbar.title.visibility = View.VISIBLE
                toolbar.button.visibility = View.VISIBLE
                toolbar.searchHere.visibility = View.GONE
            } else {
                requireActivity().finish()
            }
        }
    }

    private fun createProgram() {
        val action = ProgramsFragmentDirections.actionProgramsFragmentToProgramsCreateFragment()
        findNavController().navigate(action)
    }
}
