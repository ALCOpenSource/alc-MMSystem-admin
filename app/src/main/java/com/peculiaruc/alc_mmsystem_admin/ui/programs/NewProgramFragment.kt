package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentProgramNewBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * New program fragment
 *
 * @constructor Create empty New program fragment
 */
class NewProgramFragment : BaseFragment<FragmentProgramNewBinding>() {
    private val TAG = "ProgramNewTag"

    override val layoutIdFragment: Int = R.layout.fragment_program_new
    override val viewModel: ProgramDetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, getString(R.string.program_new_title))

        setTextViewUnderline(binding.mentorManagerSelected, getString(R.string.program_selected))
        setTextViewUnderline(binding.mentorSelected, getString(R.string.program_selected))
        setTextViewUnderline(binding.criteriaSelected, getString(R.string.program_selected))
        binding.selectCriteriaButton.setOnClickListener {
            val action =
                NewProgramFragmentDirections.actionNewProgramFragmentToSetUpCriteriaFragment()
            view.findNavController().navigate(action)
        }
        binding.createProgramButton.setOnClickListener() {
            showCreateSuccessfulDialog()
        }
    }

    /**
     * Set text view underline
     *
     * @param textview
     * @param text
     */
    private fun setTextViewUnderline(textview: TextView, text: String) {
        val mSpannableString = SpannableString(text)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        textview.text = mSpannableString
    }

    /**
     * Show create successful dialog
     *
     */
    private fun showCreateSuccessfulDialog() {
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
            successTitle.text = getString(R.string.create_program_dialog_success_message)
            val downloadImage = dialog.findViewById(R.id.success_image) as ImageView
            downloadImage.setImageResource(R.drawable.download_dialog_backgroun_2)
            val doneButton = dialog.findViewById(R.id.downloadDoneButton) as Button
            doneButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }
}