package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs.DownloadReportDialogFragment
import com.peculiaruc.alc_mmsystem_admin.ui.programs.dialogs.ShareReportDialogFragment

/*
* ProgramsReportAdapter
* */
class ProgramsReportAdapter(private val data: ArrayList<ProgramReportData>) :
    RecyclerView.Adapter<ProgramsReportAdapter.ProgramsAdapterViewHolder>() {

    class ProgramsAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val details: TextView
        val share: ImageView
        val download: ImageView

        init {
            title = view.findViewById(R.id.title)
            details = view.findViewById(R.id.details)
            share = view.findViewById(R.id.share)
            download = view.findViewById(R.id.download)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramsAdapterViewHolder {
        return ProgramsAdapterViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.program_report_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProgramsAdapterViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.details.text = data[position].title
        holder.share.setOnClickListener {
            val activity = holder.itemView.context as FragmentActivity
            val fm: FragmentManager = activity.supportFragmentManager
            val alertDialog = ShareReportDialogFragment()
            alertDialog.show(fm, "share_dialog")
        }
        holder.download.setOnClickListener {
            val activity = holder.itemView.context as FragmentActivity
            val fm: FragmentManager = activity.supportFragmentManager
            val alertDialog = DownloadReportDialogFragment()
            alertDialog.show(fm, "download_dialog")
        }
        holder.itemView.setOnClickListener {
            val action = ProgramsReportFragmentDirections.actionProgramsReportFragmentToProgramsReportDetailsFragment()
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
