package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentProgramNewBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.ProgramAdmin
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * New program fragment
 *
 * @constructor Create empty New program fragment
 */
class NewProgramFragment : BaseFragment<FragmentProgramNewBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_program_new
    override val viewModel = ProgramsViewModel.getInstance()

    private val PROGRAM_ID_ARGUMENT = "programID"
    private var programID: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.program_new_title))

        arguments?.let {
            programID = it.getInt(PROGRAM_ID_ARGUMENT)

        }
        if (programID >= 0) {
            viewModel.getProgram(programID)?.value?.let {
                updateView(it)
            }
        }
        setTextUnderline(binding.mentorManagerSelected, getString(R.string.program_selected))
        setTextUnderline(binding.mentorSelected, getString(R.string.program_selected))
        setTextUnderline(binding.criteriaSelected, getString(R.string.program_selected))
        binding.selectCriteriaButton.setOnClickListener {
            val action =
                NewProgramFragmentDirections.actionNewProgramFragmentToSetUpCriteriaFragment()
            view.findNavController().navigate(action)
        }
        binding.createProgramButton.setOnClickListener {
            openCreateProgramSuccessfullDialog()
        }
    }

    /**
     * Set text view underline
     *
     * @param textview
     * @param text
     */
    fun setTextUnderline(textview: TextView, text: String) {
        val mSpannableString = SpannableString(text)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        textview.text = mSpannableString
    }

    private fun openCreateProgramSuccessfullDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog_success)
        val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
        successTitle?.text = getString(R.string.create_program_dialog_success_message)
        val downloadImage = dialog.findViewById<ImageView>(R.id.success_image)
        downloadImage?.setImageResource(R.drawable.download_dialog_backgroun_2)
        val doneButton = dialog.findViewById<Button>(R.id.downloadDoneButton)
        doneButton?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    /**
     * update view
     *
     */
    fun updateView(program: ProgramAdmin) {
        binding.programNameEditText.setText(program.title)
        binding.programDescriptionEditText.setText(program.description)
    }
}