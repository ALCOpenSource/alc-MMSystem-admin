package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.ProgramCreateFragmentBinding
import com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs.CreateProgramDialogFragment
import java.util.*

private const val TAG = "ProgramsCreateFragmentTag"

/*
* ProgramsCreateFragment
* */
class ProgramsCreateFragment : Fragment(R.layout.program_create_fragment) {

    private var binding: ProgramCreateFragmentBinding? = null
    private lateinit var programDetailsAdapter: ProgramDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProgramCreateFragmentBinding.bind(view)

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
            this?.toolbar!!.title.text = getString(R.string.create_new_program)
            this.toolbar.button.visibility = View.GONE
            toolbar.search.visibility = View.GONE
            toolbar.back.setOnClickListener { goBack() }
            create.setOnClickListener { showProgramCreatedDialog() }
        }
    }

    private fun showProgramCreatedDialog() {
        val activity = context as FragmentActivity
        val fm: FragmentManager = activity.supportFragmentManager
        val alertDialog = CreateProgramDialogFragment()
        alertDialog.show(fm, "create_dialog")
    }

    private fun goBack() {
        TODO("Not yet implemented")
    }
}
