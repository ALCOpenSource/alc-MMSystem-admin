package com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.peculiaruc.alc_mmsystem_admin.R

/**
* DownloadReportDialogFragment
* */
class DownloadReportDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_program_report_downloaded, container)
        view.findViewById<Button>(R.id.done).setOnClickListener {
            dismiss()
        }
        return view
    }
}
