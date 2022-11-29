package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ProgramDetailsFragmentBinding
import com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs.ArchiveProgramDialogFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs.CreateProgramDialogFragment
import java.util.*

private const val TAG = "ProgramDetailsTag"

/**
* ProgramsDetailsFragment
*/
class ProgramsDetailsFragment : Fragment(R.layout.program_details_fragment) {

    private var binding: ProgramDetailsFragmentBinding? = null

    private lateinit var programDetailsAdapter: ProgramDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProgramDetailsFragmentBinding.bind(view)

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

        programDetailsAdapter = ProgramDetailsAdapter(data)

        with(binding!!) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView.hasFixedSize()
            recyclerView.adapter = programDetailsAdapter
        }
    }

    private fun setupToolbar() {
        with(binding) {
            this?.toolbar!!.title.text = getString(R.string.google_africa_scholarship_program_2022)
            toolbar.button.text = getString(R.string.create_criteria)
            toolbar.search.visibility = View.GONE

            toolbar.button.setOnClickListener { createCriteria() }
            toolbar.back.setOnClickListener { goBack() }
            delete.setOnClickListener { showDeletedDialog() }
        }
    }

    private fun createCriteria() {
        val action = ProgramsDetailsFragmentDirections.actionProgramsDetailsFragmentToCriteriaMultiChoiceFragment()
        findNavController().navigate(action)
    }

    private fun showDeletedDialog() {
        val activity = context as FragmentActivity
        val fm: FragmentManager = activity.supportFragmentManager
        val alertDialog = ArchiveProgramDialogFragment()
        alertDialog.show(fm, "delete_dialog")
    }

    private fun goBack() {
        requireActivity().finish()
    }
}
