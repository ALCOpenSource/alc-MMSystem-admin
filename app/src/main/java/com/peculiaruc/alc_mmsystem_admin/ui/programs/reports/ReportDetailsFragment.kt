package com.peculiaruc.alc_mmsystem_admin.ui.programs.reports

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
import com.peculiaruc.alc_mmsystem_admin.databinding.FragmentReportDetailsBinding
import com.peculiaruc.alc_mmsystem_admin.ui.base.BaseFragment

/**
 * Report details fragment
 *
 * @constructor Create empty Report details fragment
 */
class ReportDetailsFragment : BaseFragment<FragmentReportDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_report_details
    override val viewModel = ReportsViewModel.getInstance()

    private val REPORT_ID_ARGUMENT = "reportID"
    private var reportID = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        arguments?.let {
            reportID = it.getInt(REPORT_ID_ARGUMENT)
        }
        binding.reportCloseButton.setOnClickListener {
            view.findNavController().popBackStack()
        }
        binding.reportShareButton.setOnClickListener {
            openShareDialog()
        }
        binding.reportDownloadButton.setOnClickListener {
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
        downloadImage?.setImageResource(R.drawable.download_dialog_backgroun_2)
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