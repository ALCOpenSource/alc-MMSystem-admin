package com.peculiaruc.alc_mmsystem_admin.ui.programs.tasks

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentTaskReportDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * Task report details fragment
 *
 * @constructor Create empty Task report details fragment
 */
class TaskReportDetailsFragment : BaseFragment<FragmentTaskReportDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_task_report_details
    override val viewModel = TaskReportsViewModel.getInstance()

    private val TASK_REPORT_ID_ARGUMENT = "taskReportID"
    private var reportID = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        arguments?.let {
            reportID = it.getInt(TASK_REPORT_ID_ARGUMENT)
        }
        binding.taskReportCloseButton.setOnClickListener {
            view.findNavController().popBackStack()
        }
        binding.taskReportShareButton.setOnClickListener {
            openShareDialog()
        }
        binding.taskReportDownloadButton.setOnClickListener {
            openDownloadDialog()
        }
    }

    private fun openDownloadDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog_success)
        val doneButton = dialog.findViewById<Button>(R.id.downloadDoneButton)
        val successTitle = dialog.findViewById<TextView>(R.id.success_dialog_title)
        successTitle?.text = getString(R.string.report_download_dialog_success_message)
        val downloadImage = dialog.findViewById<ImageView>(R.id.success_image)
        downloadImage?.setImageResource(R.drawable.download_dialog_backgroun_1)
        doneButton?.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }


    private fun openShareDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.dialog_share)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        val emailButton = dialog.findViewById<Button>(R.id.shareEmailButton)
        emailButton?.setOnClickListener {
            sendEmail()
            dialog.dismiss()
        }
        cancelButton?.setOnClickListener { dialog.dismiss() }
        dialog.setCancelable(true)
        dialog.show()
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
        }
    }
}