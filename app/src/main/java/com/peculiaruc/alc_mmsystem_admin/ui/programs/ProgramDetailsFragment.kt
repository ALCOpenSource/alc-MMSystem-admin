package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
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

    override val layoutIdFragment: Int = R.layout.fragment_program_details
    override val viewModel = ProgramsViewModel.getInstance()

    private val PROGRAM_ID_ARGUMENT = "programID"
    private var programID: Int = -1
    private val TAG = "ProgramsTag"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.program_details_title))

        arguments?.let {
            programID = it.getInt(PROGRAM_ID_ARGUMENT)

        }
        Log.i(TAG, "programID:$programID")
        viewModel.getProgram(programID)?.observe(this.viewLifecycleOwner) {
            updateView(it)
        }
        Log.i(TAG, "viewmodel program:" + viewModel.getProgram(programID)?.value)
        viewModel.getProgram(programID)?.value?.let {
            updateView(it)
        }
        setTextUnderline(
            binding.deleteArchiveProgramButton,
            getString(R.string.delete_archive_program)
        )
        binding.deleteArchiveProgramButton.setOnClickListener {
            showArchivedDialog()
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

    /**
     * Show archived dialog
     *
     */
    fun showArchivedDialog() {
        activity?.let {
            val dialog = Dialog(it)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_success)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
            successTitle.text = getString(R.string.archive_program_dialog_message)
            val downloadImage = dialog.findViewById(R.id.success_image) as ImageView
            downloadImage.setImageResource(R.drawable.undraw_articles_image)
            val doneButton = dialog.findViewById(R.id.downloadDoneButton) as Button
            doneButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
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
        Log.i(TAG, "program:" + program)
        binding.programTitle.text = program.title
        binding.programDescriptionTextView.text = program.description
    }
}