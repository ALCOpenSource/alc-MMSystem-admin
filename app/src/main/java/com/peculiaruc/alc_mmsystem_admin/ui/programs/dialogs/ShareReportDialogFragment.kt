package com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.peculiaruc.alc_mmsystem_admin.R

/**
* ShareReportDialogFragment
* */
class ShareReportDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_program_share_report, container)
        view.findViewById<Button>(R.id.share).setOnClickListener {
            Toast.makeText(requireContext(), "Open Email app", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            dismiss()
        }
        return view
    }
}
