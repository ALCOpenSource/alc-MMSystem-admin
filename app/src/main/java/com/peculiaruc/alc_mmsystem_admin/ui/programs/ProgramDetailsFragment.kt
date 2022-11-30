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
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentProgramDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.domain.models.ProgramAdmin
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment


/**
 * Program details fragment
 *
 * @constructor Create empty Program details fragment
 */
class ProgramDetailsFragment : BaseFragment<FragmentProgramDetailsBinding>() {

    override val layoutIdFragment: Int =
        R.layout.fragment_program_details
    override val viewModel = ProgramsViewModel.getInstance()

    private val PROGRAM_ID_ARGUMENT = "programID"
    private var programID: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(true, getString(R.string.program_details_title))

        arguments?.let {
            programID = it.getInt(PROGRAM_ID_ARGUMENT)

        }
        viewModel.getProgram(programID)?.observe(this.viewLifecycleOwner) {
            updateView(it)
        }
        viewModel.getProgram(programID)?.value?.let {
            updateView(it)
        }
        setTextUnderline(
            binding.deleteArchiveProgramButton,
            getString(R.string.delete_archive_program)
        )
        binding.deleteArchiveProgramButton.setOnClickListener {
            openProgramArchivedDialog()
        }

        binding.cardButtonTask.setOnClickListener {
            val action =
                ProgramDetailsFragmentDirections.actionProgramDetailsFragmentToTaskReportsFragment()
            view.findNavController().navigate(action)
        }

        binding.cardButtonReport.setOnClickListener {
            val action =
                ProgramDetailsFragmentDirections.actionProgramDetailsFragmentToReportsFragment()
            view.findNavController().navigate(action)
        }

        binding.editProgramButton.setOnClickListener {
            val action =
                ProgramDetailsFragmentDirections.actionProgramDetailsFragmentToNewProgramFragment(
                    programID
                )
            view.findNavController().navigate(action)

        }
    }

    private fun openProgramArchivedDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog_success)
        val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
        successTitle?.text = getString(R.string.archive_program_dialog_message)
        val downloadImage = dialog.findViewById<ImageView>(R.id.success_image)
        downloadImage?.setImageResource(R.drawable.undraw_articles_image)
        val doneButton = dialog.findViewById<Button>(R.id.downloadDoneButton)
        doneButton?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    /**
     * Set text view underline
     *
     * @param button
     * @param text
     */
    fun setTextUnderline(button: Button, text: String) {
        val mSpannableString = SpannableString(text)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        button.text = mSpannableString
    }

    /**
     * update view
     *
     */
    fun updateView(program: ProgramAdmin) {
        binding.programTitle.text = program.title
        binding.programDescriptionTextView.text = program.description
    }
}